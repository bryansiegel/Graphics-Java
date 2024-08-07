package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.SchoolLogosDto;
import com.bryansiegel.graphicsjava.models.SchoolLogosModel;
import com.bryansiegel.graphicsjava.repositories.schoolLogosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;

@Controller
public class SchoolLogoController {

    //file upload dir
    String UPLOAD_DIR = "src/main/resources/static/files/school-logos/";

    private final schoolLogosRepository repo;

    @Autowired
    public SchoolLogoController(schoolLogosRepository repo) {
        this.repo = repo;
    }

    //index
    @GetMapping("/admin/school-logos/")
    public String schoolLogos(Model model) {

        //get current main url
        String currentUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";

        model.addAttribute("schoollogos", repo.findAll());
        model.addAttribute("currentUrl", currentUrl);
        return "admin/school-logos/index.html";
    }

    //GET create
    @GetMapping("admin/school-logos/create")
    public String showSchoolLogos(Model model) {
        SchoolLogosDto schoolLogosDto = new SchoolLogosDto();
        model.addAttribute("schoolLogosDto", schoolLogosDto);
        return "admin/school-logos/create.html";
    }

    //POST create
    @PostMapping("/admin/school-logos/create")
    public String createSchoolLogos(@Valid @ModelAttribute SchoolLogosDto schoolLogosDto, @RequestParam String formName, @RequestParam String category, SchoolLogosModel _schoolLogosModel, BindingResult result) {

        if (schoolLogosDto.getFile().isEmpty()) {
            result.addError(new FieldError("schoollogos", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/school-logos/create.html";
        }

        //save file
        MultipartFile file = schoolLogosDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();

        //SET FilePath
        String filePath = "files/school-logos/" + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                //Save to db
                _schoolLogosModel = new SchoolLogosModel();
                _schoolLogosModel.setFormName(formName);
                _schoolLogosModel.setFilePath(filePath);
                _schoolLogosModel.setCategory(category);

                repo.save(_schoolLogosModel);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/school-logos/";
    }

    //Edit
    @GetMapping("/admin/school-logos/edit/{id}")
    public String editSchoolLogos(@PathVariable Long id, Model model) {

        SchoolLogosModel schoolLogosModel = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("schoollogos", schoolLogosModel);

        return "admin/school-logos/edit.html";
    }

    //Update
    @PostMapping("/admin/school-logos/update/{id}")
    public String updateSchoolLogos(@Valid @ModelAttribute SchoolLogosDto schoolLogosDto, @PathVariable Long id, @RequestParam String formName, @RequestParam String category, @RequestParam("file") MultipartFile file, SchoolLogosModel _schoolLogosModel, BindingResult result) {

//        if (schoolLogosDto.getFile().isEmpty()) {
//            result.addError(new FieldError("schoollogos", "file", "The image file is required"));
//        }

        if (result.hasErrors()) {
            return "admin/school-logos/edit.html";
        }

        Optional<SchoolLogosModel> optionalSchoolLogosModel = repo.findById(id);

        //no file change on edit
        if (optionalSchoolLogosModel.isPresent() && !schoolLogosDto.getFile().isEmpty()) {
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();

            //SET FilePath
            String filePath = "files/school-logos/" + storageFileName;

            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                    //Save to db
                    SchoolLogosModel schoolLogosModel = optionalSchoolLogosModel.get();
                    schoolLogosModel.setFormName(formName);
                    schoolLogosModel.setFilePath(filePath);

                    repo.save(schoolLogosModel);
                }

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

        } else if (optionalSchoolLogosModel.isPresent()) {
            //Save to db
            SchoolLogosModel schoolLogosModel = optionalSchoolLogosModel.get();
            schoolLogosModel.setFormName(formName);

            repo.save(schoolLogosModel);
        }
        return "redirect:/admin/school-logos/";
    }

    //Delete
    @GetMapping("/admin/school-logos/delete/{id}")
    public String deleteSchoolLogos(@PathVariable Long id) {
        SchoolLogosModel schoolLogosModel = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(schoolLogosModel);
        return "redirect:/admin/school-logos/";

    }
}
