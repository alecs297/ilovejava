package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.TagService;
import dev.palmes.ilovejava.service.ThreadService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TagController {
    private final TagService tagService;
    private final ThreadService threadService;

    public TagController(TagService tagService, ThreadService threadService) {
        this.tagService = tagService;
        this.threadService = threadService;
    }

    /**
     * GET - Tag page mapping
     */
    @GetMapping("/tags/{id}")
    public String tag(@PathVariable("id") String id, Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) request.getSession().getAttribute("user");

        try {
            Tag tag = tagService.get(id);
            List<Thread> threads = threadService.getAllByTag(tag.getId(), 0, 10, false, user);
            request.setAttribute("pageTitle", tag.getDisplayName());
            request.setAttribute("threads", threads);
            return "content/explore";
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "errors/notAvailable";
        } catch (PermissionLevelException e) {
            model.addAttribute("error", e.getMessage());
            return "errors/notPermitted";
        }
    }

    /**
     * POST - Create a new tag
     */
    @PostMapping("/tags")
    public String createTag(String name, String description, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }

        Tag tag = new Tag();
        tag.setDescription(description);
        tag.setDisplayName(name);

        try {
            tagService.save(tag, user);
        } catch (PermissionLevelException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            model.addAttribute("error", "You don't have permission to do this");
            return "errors/notPermitted";
        }

        return "redirect:/explore";
    }

    /**
     * PUT - Update a tag
     */
    @PutMapping("/tags/{id}")
    public String updateTag(@PathVariable String id,
                            @RequestParam(required = false, defaultValue = "") String name,
                            @RequestParam(required = false, defaultValue = "") String description,
                            Model model,
                            HttpServletResponse response,
                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }

        Tag tag = new Tag();
        tag.setId(id);
        tag.setDescription(description);
        tag.setDisplayName(name);

        try {
            tagService.update(tag, user);
        } catch (PermissionLevelException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            model.addAttribute("error", "You don't have permission to do this");
            return "errors/notPermitted";
        }

        return "redirect:/explore";
    }

    /**
     * DELETE - Delete a tag
     */
    @DeleteMapping("/tags/{id}")
    public String deleteTag(@PathVariable String id, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }


        try {
            Tag tag = tagService.get(id);
            tagService.delete(tag, user);
        } catch (PermissionLevelException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            model.addAttribute("error", "You don't have permission to do this");
            return "errors/notPermitted";
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            model.addAttribute("error", "Tag not found");
            return "errors/notAvailable";
        }

        return "redirect:/explore";
    }
}
