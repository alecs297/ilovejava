package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.AlreadyExistException;
import dev.palmes.ilovejava.exceptions.InvalidFormatException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.ThreadService;
import dev.palmes.ilovejava.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {

    private final UserService userService;
    private final ThreadService threadService;

    public UserController(UserService userService, ThreadService threadService) {
        this.userService = userService;
        this.threadService = threadService;
    }

    /**
     * GET - Login page mapping
     * <p>
     * If user is already logged in, return to "/"
     * </p>
     */
    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "account/login";
    }

    /**
     * GET - Register page mapping
     * <p>
     * If user is already logged in, return to "/"
     * </p>
     */
    @GetMapping("/register")
    public String register(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "account/register";
    }

    /**
     * POST - Process login information and connect the user
     * <ul>
     *      <li>
     *          If user is already logged in, return to "/".
     *      </li>
     *      <li>
     *          On successful login redirect user to "/".
     *      </li>
     *      <li>
     *          In case of wrong login information, redirect user to login
     *          page with information on what went wrong.
     *      </li>
     * </ul>
     */
    @PostMapping("/login")
    public String login(String login, String password, Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }

        try {
            User user = userService.getUserFromCredentials(login, password);
            session.setAttribute("user", user);
            return "redirect:/";
        } catch (NotFoundException ex) {
            model.addAttribute("error", "Wrong credentials");
            model.addAttribute("login", login);
            return "account/login";
        }
    }

    /**
     * POST - Register a new user
     * <ul>
     *     <li>
     *         If user is already connected redirect to "/".
     *     </li>
     *     <li>
     *         In case of wrong register information, redirect user to
     *         register page with information on what went wrong.
     *     </li>
     *     <li>
     *         On successful register, connect user and redirect to "/".
     *     </li>
     * </ul>
     */
    @PostMapping("/register")
    public String register(String username, String email, String password, Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        try {
            userService.save(user, password);
            session.setAttribute("user", user);
            return "redirect:/";
        } catch (AlreadyExistException | InvalidFormatException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", username);
            model.addAttribute("email", email);
        }
        return "account/register";
    }

    /**
     * GET - Check if a username is available
     *
     * @return boolean in string form
     */
    @GetMapping("/check-username-available/{username}")
    @ResponseBody
    public String checkUsername(@PathVariable String username) {
        return userService.findByUsername(username).isPresent() ? "false" : "true";
    }

    /**
     * POST - Logout user by invalidating its session, then redirect to "/"
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * GET - Edit page mapping
     * <p>
     * If user is not logged in, redirect to "/login"
     * </p>
     */
    @GetMapping("/account")
    public String editAccount(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ("redirect:/login");
        }

        return ("account/edit");
    }

    /**
     * PUT - Register a new user
     * <ul>
     *     <li>
     *         If user is already connected redirect to "/".
     *     </li>
     *     <li>
     *         On successful update, update session and redirect to "/".
     *     </li>
     *     <li>
     *         In case of wrong information, redirect user to
     *         edit-account page with information on what went wrong.
     *     </li>
     * </ul>
     */
    @PutMapping("/account")
    public String editAccount(
            String originalPassword,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String newPassword,
            @RequestParam(required = false, defaultValue = "") String userId,
            Model model,
            HttpServletResponse response,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "account/login";
        }

        try {
            User tmpUser = new User();

            tmpUser.setId(userId.isBlank() ? user.getId() : UUID.fromString(userId));
            tmpUser.setPassword(newPassword);
            tmpUser.setEmail(email);
            tmpUser.setUsername(username);

            userService.update(tmpUser, user, originalPassword);
            session.setAttribute("user", userService.get(tmpUser.getId()));
            return "redirect:/";
        } catch (AlreadyExistException | PermissionLevelException | InvalidFormatException | NotFoundException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "account/edit";
        }
    }

    /**
     * GET - User Thread list
     */
    @GetMapping("/users/{username}")
    public String userThreads(@PathVariable String username, @RequestParam(required = false, defaultValue = "false") boolean removed, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get().getSafeUser());
            try {
                model.addAttribute("threads", threadService.getAllByUser(user.get(), 0, 10, removed, currentUser));
            } catch (PermissionLevelException e) {
                model.addAttribute("error", e.getMessage());
            }
            return "content/user";
        } else {

            return "errors/notAvailable";
        }
    }

    /**
     * GET - Profile page mapping
     */
    @GetMapping("/profile")
    public String profile(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ("redirect:/login");
        }

        return ("redirect:/users/" + ((User) session.getAttribute("user")).getUsername());
    }

    /**
     * GET - User posts list
     */
    @GetMapping("/users/{username}/posts")
    public String userPosts(@PathVariable String username, @RequestParam(required = false, defaultValue = "false") boolean removed, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {

            model.addAttribute("user", user.get().getSafeUser());
            try {
                model.addAttribute("threads", threadService.getAllByUser(user.get(), 0, 10, removed, currentUser));
            } catch (PermissionLevelException e) {
                model.addAttribute("error", e.getMessage());
            }
            return "content/user";
        } else {

            return "errors/notAvailable";
        }
    }
}
