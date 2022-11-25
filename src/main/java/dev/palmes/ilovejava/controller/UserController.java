package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.AlreadyExistException;
import dev.palmes.ilovejava.exceptions.InvalidFormatException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
     *          In case of wrong login information, redirect user to login
     *          page with information on what went wrong.
     *      </li>
     *      <li>
     *          On successful login redirect user to "/".
     *      </li>
     * </ul>
     */
    @PostMapping("/login")
    public String login(String login, String password, Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        User user;

        try {
            Optional<User> result = userService.findByEmail(login);
            if (result.isPresent()) {
                user = result.get();
            } else {
                result = userService.findByUsername(login);
                if (result.isPresent()) {
                    user = result.get();
                } else {
                    throw new NotFoundException();
                }
            }
            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                session.setAttribute("user", user);
                return "redirect:/";
            } else {
                throw new NotFoundException();
            }
        } catch (NotFoundException ex) {
            model.addAttribute("error", "Wrong credentials");
            model.addAttribute("login", login);
        }
        return "account/login";
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
}
