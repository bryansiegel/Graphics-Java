package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.FormDownloadsDto;
import com.bryansiegel.graphicsjava.models.FormDownloadsModel;
import com.bryansiegel.graphicsjava.repositories.formDownloadsRepository;
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
public class FormDownloadsController {

    String UPLOAD_DIR = "src/main/resources/static/files/form-downloads/";

    private final formDownloadsRepository repo;

    @Autowired
    public FormDownloadsController(formDownloadsRepository repo) {
        this.repo = repo;
    }

    //index
    @GetMapping("/admin/form-downloads/")
    public String formDownloads(Model model) {
        model.addAttribute("formdownloads", repo.findAll());
        return "admin/form-downloads/index.html";
    }

    //GET create
    @GetMapping("admin/form-downloads/create")
    public String showFormDownloads(Model model) {
        FormDownloadsDto formDownloadsDto = new FormDownloadsDto();
        model.addAttribute("formDownloadsDto", formDownloadsDto);
        return "admin/form-downloads/create.html";
    }

    //POST create
    @PostMapping("/admin/form-downloads/create")
    public String createFormDownload(@Valid @ModelAttribute FormDownloadsDto formDownloadsDto, @RequestParam String formName, @RequestParam String formType, @RequestParam String formNumber, String formMessage, FormDownloadsModel _formDownloadsModel, BindingResult result) {

//        if (formDownloadsDto.getFile().isEmpty()) {
//            result.addError(new FieldError("formDownloadsDto", "file", "The image file is required"));
//        }

        if (result.hasErrors()) {
            return "admin/form-downloads/create.html";
        }

        if (!formDownloadsDto.getFile().isEmpty()) {
            //save file
            MultipartFile file = formDownloadsDto.getFile();
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


            //SET FilePath
            String filePath = "files/form-downloads/" + storageFileName;

            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                    //Save to db
                    _formDownloadsModel = new FormDownloadsModel();
                    _formDownloadsModel.setFormName(formName);
                    _formDownloadsModel.setFilePath(filePath);
                    _formDownloadsModel.setFormType(formType);
                    _formDownloadsModel.setFormNumber(formNumber);

                    repo.save(_formDownloadsModel);
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        } else {
            //Save to db
            _formDownloadsModel = new FormDownloadsModel();
            _formDownloadsModel.setFormName(formName);
            _formDownloadsModel.setFormMessage(formMessage);
            _formDownloadsModel.setFormType(formType);
            _formDownloadsModel.setFormNumber(formNumber);

            repo.save(_formDownloadsModel);
        }

        return "redirect:/admin/form-downloads/";
    }


    //Edit
    @GetMapping("/admin/form-downloads/edit/{id}")
    public String editFormDownloads(@PathVariable Long id, Model model) {

        FormDownloadsModel formDownloads = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        model.addAttribute("formDownloads", formDownloads);


        return "admin/form-downloads/edit.html";
    }

    //Update
    @PostMapping("/admin/form-downloads/update/{id}")
    public String updateFormDownloads(@Valid @ModelAttribute FormDownloadsDto formDownloadsDto, @PathVariable Long id, @RequestParam String formName, @RequestParam String formType, @RequestParam String formNumber, FormDownloadsModel _formDownloadsModel, BindingResult result) {

        if (formDownloadsDto.getFile().isEmpty()) {
            result.addError(new FieldError("formDownloadsDto", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/form-downloads/edit.html";
        }

        //save file
        MultipartFile file = formDownloadsDto.getFile();
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

                //Save to db
                FormDownloadsModel _formdownloadsModel= repo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


                _formdownloadsModel.setFormName(formName);
                _formdownloadsModel.setFilePath(filePath);
                _formdownloadsModel.setFormType(formType);
                _formdownloadsModel.setFormNumber(formNumber);

                repo.save(_formdownloadsModel);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/admin/form-downloads/";
    }

    //Delete
    @GetMapping("/admin/form-downloads/delete/{id}")
    public String deleteFormDownloads(@PathVariable Long id) {
        FormDownloadsModel formdownloads = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(formdownloads);
        return "redirect:/admin/form-downloads/";

    }
}
