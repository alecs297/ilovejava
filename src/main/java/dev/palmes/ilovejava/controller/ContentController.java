package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.TagService;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ContentController {

    private final ThreadService threadService;
    private final TagService tagService;

    public ContentController(ThreadService threadService, TagService tagService) {
        this.threadService = threadService;
        this.tagService = tagService;
    }

    @GetMapping("/new")
    public String newpost(Model model, HttpSession session) {
        List<Tag> tags = tagService.getPublicTags(0, 100);
        model.addAttribute("tags", tags);
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
    public String explore(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(name = "removed", required = false, defaultValue = "false") boolean removed, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        try {
            List<Thread> threads = threadService.getAll(page, 10, removed, user);
            request.setAttribute("threads", threads);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            return "errors/notAvailable";
        }

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
        return "content/explore";
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
        return "content/explore";
    }
}
