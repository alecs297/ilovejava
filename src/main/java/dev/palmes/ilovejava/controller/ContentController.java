package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.PermissionLevelException;
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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public String explore(@RequestParam(defaultValue = "10", required = false) int size,
                          @RequestParam(name = "removed", required = false, defaultValue = "false") boolean removed,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");


        try {
            int max = threadService.getNumberOfPages(size, removed, user);
            List<Thread> threads = threadService.getAll((max == 0) ? 0 : ThreadLocalRandom.current().nextInt(0, max), size, removed, user);
            Collections.shuffle(threads);
            request.setAttribute("nbPages", 0);
            request.setAttribute("currentPage", 0);
            request.setAttribute("pageSize", size);
            request.setAttribute("pageTitle", "Explore");
            request.setAttribute("pageSubTitle", "Random threads, just for you");
            request.setAttribute("threads", threads);
        } catch (PermissionLevelException e) {
            request.setAttribute("error", e.getMessage());
            return "errors/notPermitted";
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
    @GetMapping("/top")
    public String trending(@RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "10", required = false) int size,
                           HttpServletRequest request) {
        List<Thread> threads = threadService.listByTop(page, size);

        try {
            request.setAttribute("nbPages", threadService.getNumberOfPages(size, false, null));
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", size);
            request.setAttribute("threads", threads);
        } catch (PermissionLevelException e) {
            request.setAttribute("error", e.getMessage());
            return "errors/notPermitted";
        }
        request.setAttribute("pageTitle", "Top");
        request.setAttribute("pageSubTitle", "Most beloved threads.");
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
    public String recent(@RequestParam(defaultValue = "0", required = false) int page,
                         @RequestParam(defaultValue = "10", required = false) int size,
                         HttpServletRequest request) {
        List<Thread> threads = threadService.listByRecent(page, size);

        try {
            request.setAttribute("nbPages", threadService.getNumberOfPages(size, false, null));
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", size);
            request.setAttribute("threads", threads);
        } catch (PermissionLevelException e) {
            request.setAttribute("error", e.getMessage());
            return "errors/notPermitted";
        }
        request.setAttribute("pageTitle", "Recent");
        request.setAttribute("pageSubTitle", "Hot new stuff, incoming !");
        request.setAttribute("threads", threads);
        return "content/explore";
    }
}
