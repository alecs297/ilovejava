package dev.palmes.ilovejava.controller;

import java.util.List;

import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ContentController {

    private final ThreadService threadService;

    public ContentController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/new")
    public String newpost(HttpSession session) {
        return session.getAttribute("user") != null ? "content/new" : "redirect:/login";
    }

    /**
     * GET - Explore page mapping
     * <p>
     * Fetches 'All the posts' and redirect to the "/explore" view
     * </p>
     */
    //@GetMapping("/explore")
    public String explore(HttpServletRequest request) {
        String content = "";

        try {
            List<Thread> threads = threadService.getAll(1, 10);
            if (threads.size() == 0) { throw new NotFoundException(); }
            content += "[DBG] - Threads fetched : " + threads.size() + "<br>";
            for (Thread thread:threads) {
                content += thread.getTitle()+"<br>";
            }
        } catch (NullPointerException | NotFoundException e) {
            content = "<h1>An error occurred while fetching the threads.</h1>";
        }

        request.setAttribute("content", content);
        return "content/posts";
    }

    /**
     * GET - Trending page mapping
     * <p>
     * Fetches the most popular posts and redirect to the "/explore" view
     * </p>
     */
    //@GetMapping("/trending")
    public String trending(HttpServletRequest request) {
        String content = "";

        try {
            List<Thread> threads = threadService.listByPopular(1, 10);
            if (threads.size() == 0) { throw new NotFoundException(); }
            content += "[DBG] - Threads fetched : " + threads.size() + "<br>";
            for (Thread thread:threads) {
                content += thread.getTitle()+"<br>";
            }
        } catch (NullPointerException | NotFoundException e) {
            content = "<h1>An error occurred while fetching the threads.</h1>";
        }

        request.setAttribute("content", content);
        return "content/posts";
    }

    /**
     * GET - Recent page mapping
     * <p>
     * Fetches the latest posts and redirect to the "/explore" view
     * </p>
     */
    //@GetMapping("/recent")
    public String recent(HttpServletRequest request) {
        String content = "";

        try {
            List<Thread> threads = threadService.listByRecent(1, 10);
            if (threads.size() == 0) { throw new NotFoundException(); }
            content += "[DBG] - Threads fetched : " + threads.size() + "<br>";
            for (Thread thread:threads) {
                content += thread.getTitle()+"<br>";
            }
        } catch (NullPointerException | NotFoundException e) {
            content = "<h1>An error occurred while fetching the threads.</h1>";
        }

        request.setAttribute("content", content);
        return "content/posts";
    }
}
