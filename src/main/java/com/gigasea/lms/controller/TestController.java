package com.gigasea.lms.controller;

import com.gigasea.lms.model.TestResult; // Import the TestResult class// Import the repository
import com.gigasea.lms.service.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestResultRepository testResultRepository; // Inject the repository

    @GetMapping("/takeTest")
    public String takeTest(Model model) {
        return "takeTest"; // This will return the takeTest.html page
    }

    @PostMapping("/submitTest")
    public String submitTest(@RequestParam Map<String, String> answers,
                             @RequestParam String name, // New parameter for the user's name
                             Model model) {
        int score = 0;

        // Example answer checking
        if ("C".equals(answers.get("q1"))) score++;
        if ("B".equals(answers.get("q2"))) score++;
        // Add more checks for the remaining questions

        // Save the result to the database
        TestResult testResult = new TestResult(name, score); // Pass the name and score
        testResultRepository.save(testResult);

        model.addAttribute("score", score);
        model.addAttribute("name", name); // Add the name to the model for display
        return "result"; // This will return the result.html page
    }
}