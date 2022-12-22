package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.PostService;
import dev.palmes.ilovejava.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * GET - Post page mapping
     */
    @GetMapping("/posts/{id}")
    public String post(@PathVariable UUID id) {
        try {
            Post post = postService.get(id);
            return "redirect:/threads/" + post.getThread().getId() + "#" + id;
        } catch (NotFoundException | NotAvailableException e) {
            return "errors/notAvailable";
        }
    }


    /**
     * POST - Post a reply to a post
     * <p>
     * Create a new post and set the parent to the post that is being replied to
     * and the thread to the thread that the post is in
     * </p>
     */
    @PostMapping("/posts/{id}")
    public String replyPost(@PathVariable String id, @RequestParam("content") String content, HttpServletResponse response, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }

        UUID uuid = UUID.fromString(id);

        Post parent;
        try {
            parent = this.postService.get(uuid);
        } catch (NotFoundException | NotAvailableException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "redirect:/explore";
        }

        if (content.length() > Post.MAX_CONTENT_SIZE) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            model.addAttribute("error", "Content is too long");
            return "redirect:/posts/" + id;
        }

        Post post = new Post();
        post.setContent(content);
        post.setAuthor(user);
        post.setParent(parent);
        post.setThread(parent.getThread());

        postService.save(post);

        return "redirect:/threads/" + parent.getThread().getId();
    }

    /**
     * POST - Vote for post
     */
    @PostMapping("/posts/{id}/vote")
    @ResponseBody
    public String votePost(@PathVariable String id, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }

        UUID uuid = UUID.fromString(id);

        Post post;
        try {
            post = this.postService.get(uuid);
            postService.toggleVote(post, user);
            userService.countUserVotes(post.getAuthor());
            return post.getVotesCount() + "";
        } catch (NotFoundException | NotAvailableException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "404";
        }
    }

    /**
     * PUT - Update a post
     * <p>
     * Update a post content
     * </p>
     */
    @PutMapping("/posts/{id}")
    public String updatePost(@PathVariable String id, String content, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }

        UUID uuid = UUID.fromString(id);

        if (content.length() > Post.MAX_CONTENT_SIZE) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            model.addAttribute("error", "Content is too long");
            return "redirect:/posts/" + id;
        }

        try {
            Post post = new Post();
            post.setContent(content);
            post.setId(uuid);

            this.postService.update(post, user);

        } catch (PermissionLevelException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            model.addAttribute("error", "You don't have permission to do this");
            return "errors/notPermitted";
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "redirect:error/notAvailable";
        }

        return "redirect:/threads/" + id;
    }

    /**
     * DELETE - Delete a post
     */
    @DeleteMapping("/posts/{id}")
    @ResponseBody
    public String deletePost(@PathVariable String id, Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "redirect:/login";
        }
        try {
            this.postService.delete(postService.get(UUID.fromString(id)), user);
        } catch (PermissionLevelException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            model.addAttribute("error", "You don't have permission to do this");
            return "errors/notPermitted";
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "redirect:error/notAvailable";
        }

        return "";
    }
}
