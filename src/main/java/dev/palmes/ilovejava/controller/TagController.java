package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.AlreadyExistException;
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
    @ResponseBody
    public String createTag(String id, String name, String description, Model model, HttpServletResponse response, HttpSession session) {
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
            tagService.save(tag, user);

        } catch (AlreadyExistException e) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            return e.getMessage();
        } catch (PermissionLevelException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return "redirect:/error";
        }
        return "";
    }

    /**
     * PUT - Update a tag
     */
    @PutMapping("/tags/{id}")
    @ResponseBody
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
            return "redirect:/error";
        }

        return "";
    }

    /**
     * DELETE - Delete a tag
     */
    @DeleteMapping("/tags/{id}")
    @ResponseBody
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
            return "redirect:/error";
        } catch (NotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "redirect:/tags";
        }

        return "";
    }

    @GetMapping("/tags")
    public String manageTags(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (!user.isAdmin()) {
            return "errors/notPermitted";
        }

        try {
            List<Tag> tags = tagService.getAll(0, 1000, user);
            model.addAttribute("tags", tags);
            return "content/admin_edit_tags";
        } catch (PermissionLevelException e) {
            return "errors/notPermitted";
        }
    }
}
