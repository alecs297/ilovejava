package dev.palmes.ilovejava.controller;

import dev.palmes.ilovejava.exceptions.AlreadyExistException;
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

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "account/login";
    }

    @GetMapping("/register")
    public String register(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "account/register";
    }

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
        } catch (AlreadyExistException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", username);
            model.addAttribute("email", email);
        }
        return "account/register";
    }

    @GetMapping("/check-username-available/{username}")
    @ResponseBody
    public String checkUsername(@PathVariable String username) {
        return userService.findByUsername(username).isPresent() ? "false" : "true";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
