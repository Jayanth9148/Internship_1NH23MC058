package com.gigasea.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyPolicyController {

    // Display the Privacy Policy page
    @GetMapping("/privacy")
    public String showPrivacyPolicyPage(Model model) {
        return "privacy"; // Return the privacy HTML page
    }
}