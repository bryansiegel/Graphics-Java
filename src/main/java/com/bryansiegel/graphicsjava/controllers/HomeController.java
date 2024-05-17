package com.bryansiegel.graphicsjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "public/home";
    }

    @GetMapping("/forms-archives")
    public String formsArchives() {
        return "public/forms-archives";
    }

    @GetMapping("/templates-logos")
    public String templatesLogos() {
        return "public/templates-logos";
    }
}
