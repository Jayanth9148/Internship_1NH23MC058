package com.gigasea.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsController {

    // Display the Terms of Service page
    @GetMapping("/terms")
    public String showTermsPage(Model model) {
        return "terms"; // Return the terms HTML page
    }
}