package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ContentController {

    private final ThreadService threadService;

    public ContentController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/new")
    public String newpost(Model model, HttpSession session) {
        model.addAttribute("error", "");
        return session.getAttribute("user") != null ? "content/new" : "redirect:/login";
    }

    /**
     * GET - Explore page mapping
     * <p>
     * Fetches 'All the posts' and redirect to the "/explore" view
     * </p>
     */
    @GetMapping("/explore")
    public String explore(HttpServletRequest request) {

        List<Thread> threads = threadService.getAll(0, 10);

        request.setAttribute("threads", threads);
        return "content/explore";
    }

    /**
     * GET - Trending page mapping
     * <p>
     * Fetches the most popular posts and redirect to the "/explore" view
     * </p>
     */
    @GetMapping("/trending")
    public String trending(HttpServletRequest request) {
        List<Thread> threads = threadService.listByPopular(0, 10);

        request.setAttribute("threads", threads);
        return "content/trending";
    }

    /**
     * GET - Recent page mapping
     * <p>
     * Fetches the latest posts and redirect to the "/explore" view
     * </p>
     */
    @GetMapping("/recent")
    public String recent(HttpServletRequest request) {
        List<Thread> threads = threadService.listByRecent(0, 10);

        request.setAttribute("threads", threads);
        return "content/recent";
    }
}
