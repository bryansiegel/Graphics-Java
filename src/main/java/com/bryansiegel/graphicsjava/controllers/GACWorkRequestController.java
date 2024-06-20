package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.GACWorkRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class GACWorkRequestController {

    private JavaMailSender mailSender;

    @GetMapping("/contact")
    public String Contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String PostContact(@ModelAttribute GACWorkRequestDto gacWorkRequestDto, Model model) {

        String full_name = gacWorkRequestDto.getFull_name();
        String email = gacWorkRequestDto.getEmail();
        String phone = gacWorkRequestDto.getPhone();
        String location_department = gacWorkRequestDto.getLocation_department();
        String project_name = gacWorkRequestDto.getProject_name();
        String google_drive_link = gacWorkRequestDto.getGoogle_drive_link();
        String message = gacWorkRequestDto.getMessage();
        MultipartFile GAC_work_request_form = gacWorkRequestDto.getGAC_work_request_form();
        MultipartFile file_uploads = gacWorkRequestDto.getFile_uploads();

        // Send the email
        try {
            sendEmail(name, email, message, attachment);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while sending the email.");
            return "contact";
        }

        // Save the GAC_work_request_form to a directory
        if (!GAC_work_request_form.isEmpty()) {
            try {
                byte[] bytes = GAC_work_request_form.getBytes();
                Path path = Paths.get("uploads/" + GAC_work_request_form.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save the GAC_work_request_form to a directory
        if (!file_uploads.isEmpty()) {
            try {
                byte[] bytes = file_uploads.getBytes();
                Path path = Paths.get("uploads/" + file_uploads.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "contact";
    }

    private void sendEmail(String name, String email, String message, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("your-email@gmail.com");
        helper.setTo("recipient-email@example.com");
        helper.setSubject("New Contact Form Submission");
        helper.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

        if (!attachment.isEmpty()) {
            InputStreamSource attachmentSource = new ByteArrayResource(attachment.getBytes());
            helper.addAttachment(attachment.getOriginalFilename(), attachmentSource);
        }

        mailSender.send(mimeMessage);
    }
}
}
