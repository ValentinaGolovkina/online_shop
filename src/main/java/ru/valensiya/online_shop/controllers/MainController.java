package ru.valensiya.online_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/nav")
    public String showNavigation() {
        return "nav";
    }
}
