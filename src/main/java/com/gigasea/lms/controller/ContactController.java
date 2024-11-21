package com.gigasea.lms.controller;

import com.gigasea.lms.model.ContactMessage;
import com.gigasea.lms.service.ContactService; // Assuming you have a service for handling contact messages
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService; // Service to handle contact messages

    // Display the contact information page
    @GetMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("contactMessage", new ContactMessage()); // Create a new ContactMessage object
        return "contact"; // Return the contact HTML page
    }

    // Handle the submission of the contact form
    @PostMapping("/sendContact")
    public String sendContact(@ModelAttribute("contactMessage") ContactMessage contactMessage) {
        // Save the contact message using the service
        contactService.saveContactMessage(contactMessage);

        // Define the path to the Excel file
        String excelFilePath = "example.xlsx";
        Workbook workbook;
        Sheet sheet;

        // Check if the file already exists
        try {
            // Try to open the existing file
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(0); // Get the first sheet
        } catch (IOException e) {
            // If the file does not exist, create a new workbook and sheet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Contact Information");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Email");
            headerRow.createCell(2).setCellValue("Message");
        }

        // Create a new row for the contact information
        int lastRowNum = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRowNum + 1);
        row.createCell(0).setCellValue(contactMessage.getName());
        row.createCell(1).setCellValue(contactMessage.getEmail());
        row.createCell(2).setCellValue(contactMessage.getMessage());

        // Write the output to the file
        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Redirect back to the contact page with a success parameter
        return "redirect:/contact?success";
    }
}