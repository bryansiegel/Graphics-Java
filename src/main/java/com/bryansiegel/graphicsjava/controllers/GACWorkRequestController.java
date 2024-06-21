package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.GACWorkRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
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

    @Autowired
    private JavaMailSender mailSender;

//    @GetMapping("/contact")
//    public String Contact() {
//        return "contact";
//    }

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
        MultipartFile[] file_uploads = gacWorkRequestDto.getFile_uploads();

        // Send the email
        try {
            sendEmail(full_name, email, phone, location_department, project_name, google_drive_link, message, GAC_work_request_form, file_uploads);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while sending the email.");
            return "public/forms/thank-you.html";
        }

        // Save the GAC_work_request_form to a directory
        if (!GAC_work_request_form.isEmpty()) {
            try {
                byte[] bytes = GAC_work_request_form.getBytes();
                Path path = Paths.get("src/main/resources/static/files/GAC-work-requests" + GAC_work_request_form.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save the GAC_work_request_form to a directory
//        if (!file_uploads.isEmpty()) {
//            try {
//                byte[] bytes = file_uploads.getBytes();
//                Path path = Paths.get("src/main/resources/static/files/GAC-work-requests" + file_uploads.getOriginalFilename());
//                Files.write(path, bytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return "public/forms/thank-you.html";
    }

    @GetMapping("/thank-you")
    public String ThankYou() {
        return "public/forms/thank-you.html";
    }

    private void sendEmail(String full_name, String email, String phone, String location_department, String project_name, String google_drive_link, String message, MultipartFile GAC_work_request_form, MultipartFile[] file_uploads) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("ccsdcommsunit@gmail.com");
        helper.setTo("siegebm@nv.ccsd.net");
        helper.setSubject("GAC Work Request Form Submission");
        helper.setText("Name: " + full_name + "\nEmail: " + email + "\nMessage: " + message);

        if (!GAC_work_request_form.isEmpty()) {
            InputStreamSource attachmentSource = new ByteArrayResource(GAC_work_request_form.getBytes());
            helper.addAttachment(GAC_work_request_form.getOriginalFilename(), attachmentSource);
        }


//        if (!file_uploads.isEmpty()) {
//            InputStreamSource attachmentSource = new ByteArrayResource(file_uploads.getBytes());
//            helper.addAttachment(file_uploads.getOriginalFilename(), attachmentSource);
//        }

        for (MultipartFile attachment : file_uploads) {
            if (!attachment.isEmpty()) {
                InputStreamSource attachmentSource = new ByteArrayResource(attachment.getBytes());
                helper.addAttachment(attachment.getOriginalFilename(), attachmentSource);
            }
        }

        mailSender.send(mimeMessage);
    }
}

