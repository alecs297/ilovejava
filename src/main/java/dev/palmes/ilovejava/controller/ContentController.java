package dev.palmes.ilovejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
/*
    private final ThreadService threadService;

    public ContentController(ThreadService threadService) {
        this.threadService = threadService;
    }
*/
    /**
     * GET - Explore page mapping
     * <p>
     * Redirect to the "/explore" view
     * </p>
     */
    @GetMapping("/explore")
    public String explore() {
        return "content/posts";
    }

    /**
     * GET - Explore page mapping
     * <p>
     * Redirect to the "/explore" view
     * </p>
     */
    @GetMapping("/trending")
    public String trending() {
        return "content/posts";
    }

    /**
     * GET - Explore page mapping
     * <p>
     * Redirect to the "/explore" view
     * </p>
     */
    @GetMapping("/recent")
    public String recent() {
        return "content/posts";
    }
}
