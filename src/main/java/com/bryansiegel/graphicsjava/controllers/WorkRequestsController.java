package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.WorkRequestsDto;
import com.bryansiegel.graphicsjava.models.WorkRequestsModel;
import com.bryansiegel.graphicsjava.repositories.workRequestsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Controller
public class WorkRequestsController {

    String UPLOAD_DIR = "src/main/resources/static/files/work-requests/";

    private final workRequestsRepository repo;

    @Autowired

    public WorkRequestsController(workRequestsRepository repo) {
        this.repo = repo;
    }

    //index
    @GetMapping("/admin/work-requests/")
    public String workRequests(Model model) {
        model.addAttribute("workrequests", repo.findAll());
        return "admin/work-requests/index.html";
    }

    //GET create
    @GetMapping("admin/work-requests/create")
    public String showWorkRequests(Model model) {
        WorkRequestsDto workRequestsDto = new WorkRequestsDto();
        model.addAttribute("workRequestsDto", workRequestsDto);
        return "admin/work-requests/create.html";
    }

    //POST create
    @PostMapping("/admin/work-requests/create")
    public String createWorkRequests(
            @Valid
            @ModelAttribute WorkRequestsDto workRequestsDto,
            @RequestParam String fullName,
            @RequestParam String emailAddress,
            @RequestParam String phoneNumber,
            @RequestParam String locationDepartmentName,
            @RequestParam String projectName,
            @RequestParam String googleDriveLink,
            @RequestParam String message,
            WorkRequestsModel _workRequestsModelModel,
            BindingResult result) {

        if (workRequestsDto.getFile().isEmpty()) {
            result.addError(new FieldError("WorkRequestsDto", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/work-requests/create.html";
        }

        //save file
        MultipartFile file = workRequestsDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


        //SET FilePath
        String filePath = "files/work-request/" + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                //Save to db
                _workRequestsModelModel = new WorkRequestsModel();
                _workRequestsModelModel.setFullName(fullName);
                _workRequestsModelModel.setEmailAddress(emailAddress);
                _workRequestsModelModel.setPhoneNumber(phoneNumber);
                _workRequestsModelModel.setLocationDepartmentName(locationDepartmentName);
                _workRequestsModelModel.setProjectName(projectName);
                _workRequestsModelModel.setGoogleDriveLink(googleDriveLink);
                _workRequestsModelModel.setMessage(message);
                _workRequestsModelModel.setFilePath(filePath);

                repo.save(_workRequestsModelModel);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

//        TODO: Change to public page redirect
        return "redirect:/admin/work-requests/";
    }

    //Edit
    @GetMapping("/admin/work-requests/edit/{id}")
    public String editWorkRequests(@PathVariable Long id, Model model) {
        WorkRequestsModel workRequestsModel = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("workrequests", workRequestsModel);
        return "admin/work-requests/edit.html";
    }

    //Update
    @PostMapping("/admin/work-requests/update/{id}")
    public String updateCurrentEvaluations(
            @Valid
            @ModelAttribute WorkRequestsDto workRequestsDto,
            @RequestParam String fullName,
            @RequestParam String emailAddress,
            @RequestParam String phoneNumber,
            @RequestParam String locationDepartmentName,
            @RequestParam String projectName,
            @RequestParam String googleDriveLink,
            @RequestParam String message,
            WorkRequestsModel _workRequestsModelModel,
            BindingResult result) {

        if (workRequestsDto.getFile().isEmpty()) {
            result.addError(new FieldError("workRequestsDto", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/work-requests/edit.html";
        }

        //save file
        MultipartFile file = workRequestsDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


        //SET FilePath
        String filePath = UPLOAD_DIR + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/admin/work-requests/";
    }

    //Delete
    @GetMapping("/admin/work-requests/delete/{id}")
    public String deleteWorkRequest(@PathVariable Long id) {
        WorkRequestsModel workRequestsModel = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(workRequestsModel);
        return "redirect:/admin/work-requests/";
    }
}
