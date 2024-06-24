package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.IndexOfFormsDto;
import com.bryansiegel.graphicsjava.models.FormDownloadsModel;
import com.bryansiegel.graphicsjava.models.IndexOfFormsModel;
import com.bryansiegel.graphicsjava.repositories.indexOfFormsRepository;
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
import java.util.Optional;

@Controller
public class IndexOfFormsController {

//file upload dir
    String UPLOAD_DIR = "src/main/resources/static/files/index-of-forms/";

    @Autowired
    private final indexOfFormsRepository repo;

    public IndexOfFormsController(indexOfFormsRepository repo) {
        this.repo = repo;
    }

    //index
    @GetMapping("/admin/index-of-forms/")
    public String indexOfForms(Model model) {
        model.addAttribute("indexofforms", repo.findAll());
        return "admin/index-of-forms/index.html";
    }

    //GET create
    @GetMapping("admin/index-of-forms/create")
    public String showCreatePage(Model model) {
        IndexOfFormsDto indexOfFormsDto = new IndexOfFormsDto();
        model.addAttribute("indexOfFormsDto", indexOfFormsDto);
        return "admin/index-of-forms/create.html";
    }

    //POST create
    @PostMapping("/admin/index-of-forms/create")
    public String createIndexOfForm(@Valid @ModelAttribute IndexOfFormsDto indexOfFormsDto, @RequestParam String formName, String formMessage, IndexOfFormsModel _indexOfFormsModel, BindingResult result) {

        if (indexOfFormsDto.getFile().isEmpty()) {
            result.addError(new FieldError("indexofforms", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/index-of-forms/create.html";
        }

            //save file
            MultipartFile file = indexOfFormsDto.getFile();
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


            //SET FilePath
            String filePath = "files/index-of-forms/" + storageFileName;


            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                }

                //Save to db
                _indexOfFormsModel = new IndexOfFormsModel();
//                _indexOfFormsModel.setFormMessage(formMessage);
                _indexOfFormsModel.setFormName(formName);
                _indexOfFormsModel.setFilePath(filePath);

                repo.save(_indexOfFormsModel);

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }



        return "redirect:/admin/index-of-forms/";
    }

    //Edit
    @GetMapping("/admin/index-of-forms/edit/{id}")
    public String editIndexOfForms(@PathVariable Long id, Model model) {

        IndexOfFormsModel indexofforms = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        model.addAttribute("indexofforms", indexofforms);


        return "admin/index-of-forms/edit.html";
    }

    //Update
    @PostMapping("/admin/index-of-forms/update/{id}")
    public String updateIndexOfForms(@Valid @ModelAttribute IndexOfFormsDto indexOfFormsDto, @PathVariable Long id, @RequestParam String formName, @RequestParam("file") MultipartFile file, BindingResult result) {

//        if (indexOfFormsDto.getFile().isEmpty()) {
//            result.addError(new FieldError("indexOfFormsDto", "file", "The image file is required"));
//        }

        if (result.hasErrors()) {
            return "admin/index-of-forms/edit.html";
        }

        Optional<IndexOfFormsModel> optionalIndexOfFormsModel = repo.findById(id);

        if (optionalIndexOfFormsModel.isPresent()) {
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
                    IndexOfFormsModel indexOfFormsModel = optionalIndexOfFormsModel.get();
                    indexOfFormsModel.setFormName(formName);
                    indexOfFormsModel.setFilePath(filePath);

                    repo.save(indexOfFormsModel);
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }

        //save file
//        MultipartFile file = indexOfFormsDto.getFile();
//        Date createdAt = new Date();
//        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();
//
//
//        //SET FilePath
//        String filePath = UPLOAD_DIR + storageFileName;
//
//        try {
//            Path uploadPath = Paths.get(UPLOAD_DIR);
//
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//
//            try (InputStream inputStream = file.getInputStream()) {
//                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);
//
//                //Save to db
//                IndexOfFormsModel _indexofforms = repo.findById(id)
//                        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//
//                _indexofforms.setFormName(formName);
//                _indexofforms.setFilePath(filePath);
//
//                repo.save(_indexofforms);
//            }
//        } catch (Exception ex) {
//            System.out.println("Exception: " + ex.getMessage());
//        }


        return "redirect:/admin/index-of-forms/";
    }

    //Delete
    @GetMapping("/admin/index-of-forms/delete/{id}")
    public String deleteIndexOfForms(@PathVariable Long id) {
        IndexOfFormsModel indexofforms = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(indexofforms);
        return "redirect:/admin/index-of-forms/";

    }

}
