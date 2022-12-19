package dev.palmes.ilovejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("404")
    public String error404() {
        return ("errors/notAvailable");
    }

    @GetMapping("/error")
    public String error() {
        return ("errors/notAvailable");
    }
}