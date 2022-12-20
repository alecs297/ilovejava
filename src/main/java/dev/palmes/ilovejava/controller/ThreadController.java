package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.PostService;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ThreadController {

    private final ThreadService threadService;
    private final PostService postService;

    public ThreadController(ThreadService threadService, PostService postService) {
        this.threadService = threadService;
        this.postService = postService;
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
     *     Mark a thread as deleted
     * </p>
     */
}
