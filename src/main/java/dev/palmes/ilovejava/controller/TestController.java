package dev.palmes.ilovejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test-not-found")
    public String get() {
        return "errors/notAvailable";
    }
}
