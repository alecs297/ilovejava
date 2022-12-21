package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.PostService;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class ThreadController {

    private final ThreadService threadService;
    private final PostService postService;

    public ThreadController(ThreadService threadService, PostService postService) {
        this.threadService = threadService;
        this.postService = postService;
    }

    /**
     * GET - Thread
     */
    @GetMapping("/threads/{id}")
    public ModelAndView thread(@PathVariable String id, Model model, HttpSession session) {
        //TODO: Change view path
        ModelAndView modelAndView = new ModelAndView("content/thread");

        UUID uuid = UUID.fromString(id);

        try {
            Thread thread = this.threadService.get(uuid);
            modelAndView.addObject("thread", thread);
        } catch (NotFoundException | NotAvailableException e) {
            modelAndView.setViewName("errors/notAvailable");
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

    /**
     * POST - Create a new Thread
     * <p>
     * Create a new thread and the first post in it
     * </p>
     */
    @PostMapping("/threads")
    public String newThread(String title, String content, HttpServletResponse response, HttpSession session) {
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

        post.setThread(thread);

        this.threadService.save(thread);

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
     * POST - Create a new Post in a Thread
     * <p>
     * Create a new post in a thread
     * </p>
     */
    @PostMapping("/threads/{id}")
    public String newPost(@PathVariable String id, String content, HttpServletResponse response, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        try {
            System.out.println("ID: " + id);
            Post parent = threadService.get(UUID.fromString(id)).getEntry();

            if (content.length() > Post.MAX_CONTENT_SIZE) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                model.addAttribute("error", "Content is too long");
                return "redirect:/threads/" + id;
            }

            Post post = new Post();
            post.setContent(content);
            post.setAuthor(user);
            post.setParent(parent);
            post.setThread(parent.getThread());

            postService.save(post);

            return "redirect:/threads/" + parent.getThread().getId();
        } catch (NotFoundException | NotAvailableException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "errors/notAvailable";
        }


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
