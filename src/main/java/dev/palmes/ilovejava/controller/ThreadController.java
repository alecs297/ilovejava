package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.TagService;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class ThreadController {

    private final ThreadService threadService;
    private final TagService tagService;

    public ThreadController(ThreadService threadService, TagService tagService) {
        this.threadService = threadService;
        this.tagService = tagService;
    }

    /**
     * GET - Thread
     */
    @GetMapping("/threads/{id}")
    public String thread(@PathVariable String id, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        UUID uuid = UUID.fromString(id);

        try {
            Thread thread = this.threadService.get(uuid, user);
            model.addAttribute("thread", thread);
        } catch (NotFoundException | NotAvailableException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            model.addAttribute("error", e.getMessage());
            return "errors/notAvailable";
        }
        return "content/thread";
    }

    /**
     * POST - Create a new Thread
     * <p>
     * Create a new thread and the first post in it
     * </p>
     */
    @PostMapping("/threads")
    public String newThread(String title, String content, @RequestParam(name = "tag") List<String> Tags, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }


        Post post = new Post();
        post.setContent(content);
        post.setAuthor(user);

        Thread thread = new Thread();
        thread.setTitle(title);
        thread.setEntry(post);

        for (String tag : Tags) {
            try {
                thread.addTag(this.tagService.get(tag));
            } catch (NotFoundException e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return "redirect:/";
            }
        }

        post.setThread(thread);

        threadService.save(thread);

        return "redirect:/threads/" + thread.getId();
    }

    /**
     * PUT - Update a thread
     * <p>
     * Update a thread title
     * </p>
     */
    @PutMapping("/threads/{id}")
    public String updateThread(@PathVariable String id, String title, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }

        UUID uuid = UUID.fromString(id);

        try {
            Thread thread = this.threadService.get(uuid);
            // TODO: Move to service
            if (thread.getEntry().getThread().getId().equals(user.getId()) || user.isAdmin()) {
                thread.setTitle(title);
                this.threadService.save(thread);
            } else {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                model.addAttribute("error", "You don't have permission to do this");
                return "errors/notPermitted";
            }
        } catch (NotFoundException | NotAvailableException e) {
            return "redirect:/errors/notAvailable";
        }

        return "redirect:/threads/" + id;
    }


    /**
     * DELETE - Delete a Thread
     * <p>
     * Mark a thread as deleted
     * </p>
     */
    @DeleteMapping("/threads/{id}")
    public String deleteThread(Thread thread, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }
        if (thread == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "errors/notAvailable";
        }
        try {
            threadService.delete(thread, user);
        } catch (PermissionLevelException e) {
            model.addAttribute("error", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return "errors/notPermitted";
        }

        return "redirect:/explore";
    }


}
