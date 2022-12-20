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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        ModelAndView modelAndView = new ModelAndView("thread/thread");

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
    public String newThread(String title, String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
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

        return "redirect:/explore";
    }

    /**
     * POST - Create a new Post in a Thread
     * <p>
     * Create a new post in a thread
     * </p>
     */
    @PostMapping("/threads/{id}")
    public String newPost(Post parent, String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (parent == null) {
            return "redirect:/explore";
        }

        Post post = new Post();
        post.setContent(content);
        post.setAuthor(user);
        post.setParent(parent);
        post.setThread(parent.getThread());

        postService.save(post);

        return "redirect:/explore";
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
            return "redirect:/login";
        }
        if (thread == null) {
            return "redirect:/explore";
        }
        try {
            threadService.delete(thread, user);
        } catch (PermissionLevelException e) {
            model.addAttribute("error", e.getMessage());
            response.setStatus(403);
            return "redirect:/error/notPermitted";
        }

        return "redirect:/explore";
    }

}
