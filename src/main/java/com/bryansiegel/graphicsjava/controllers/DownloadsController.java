package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.DownloadsDto;
import com.bryansiegel.graphicsjava.models.DownloadsModel;
import com.bryansiegel.graphicsjava.repositories.downloadsRepository;
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
public class DownloadsController {

    //file upload dir
    String UPLOAD_DIR = "src/main/resources/static/files/downloads/";

    @Autowired
    private final downloadsRepository repo;

    public DownloadsController( downloadsRepository repo) {
        this.repo = repo;
    }

    //index
    @GetMapping("/admin/downloads/")
    public String downloads(Model model) {
        model.addAttribute("downloads", repo.findAll());
        return "admin/downloads/index.html";
    }

    //GET create
    @GetMapping("admin/downloads/create")
    public String showCreatePage(Model model) {
        DownloadsDto downloadsDto = new DownloadsDto();
        model.addAttribute("downloadsDto", downloadsDto);
        return "admin/downloads/create.html";
    }

    //POST create
    @PostMapping("/admin/downloads/create")
    public String createDownload(@Valid @ModelAttribute DownloadsDto downloadsDto, @RequestParam String formName, @RequestParam String category,  DownloadsModel _downloadsModel, BindingResult result) {

        if (downloadsDto.getFile().isEmpty()) {
            result.addError(new FieldError("downloadsDto", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/downloads/create.html";
        }

        //save file
        MultipartFile file = downloadsDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


        //SET FilePath
        String filePath = "files/downloads/" + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                //Save to db
                _downloadsModel = new DownloadsModel();
                _downloadsModel.setFormName(formName);
                _downloadsModel.setFilePath(filePath);
                _downloadsModel.setCategory(category);

                repo.save(_downloadsModel);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/downloads/";
    }

    //Edit
    @GetMapping("/admin/downloads/edit/{id}")
    public String editDownloads(@PathVariable Long id, Model model) {

        DownloadsModel downloadsModel = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        model.addAttribute("downloads", downloadsModel);


        return "admin/downloads/edit.html";
    }

    //Update
    @PostMapping("/admin/downloads/update/{id}")
    public String updateDownloads(@Valid @ModelAttribute DownloadsDto downloadsDto, @PathVariable Long id, @RequestParam String formName, @RequestParam String category, @RequestParam("file") MultipartFile file, DownloadsModel _downloadsModel, BindingResult result) {

//        if (downloadsDto.getFile().isEmpty()) {
//            result.addError(new FieldError("downloadsDto", "file", "The image file is required"));
//        }

        if (result.hasErrors()) {
            return "admin/downloads/edit.html";
        }

        Optional<DownloadsModel> optionalDownloadsModel = repo.findById(id);

        //no file change on edit
        if (optionalDownloadsModel.isPresent() && !downloadsDto.getFile().isEmpty()) {
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();

            //SET FilePath
            String filePath = "files/downloads/" + storageFileName;

            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                    //Save to db
                    DownloadsModel downloadsModel = optionalDownloadsModel.get();
                    downloadsModel.setFormName(formName);
                    downloadsModel.setFilePath(filePath);

                    repo.save(downloadsModel);
                }

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

        } else if(optionalDownloadsModel.isPresent()) {
            //Save to db
            DownloadsModel downloadsModel = optionalDownloadsModel.get();
            downloadsModel.setFormName(formName);

            repo.save(downloadsModel);
        }

        return "redirect:/admin/downloads/";
    }

    //Delete
    @GetMapping("/admin/downloads/delete/{id}")
    public String deleteDownloads(@PathVariable Long id) {
        DownloadsModel downloadsModel = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(downloadsModel);
        return "redirect:/admin/downloads/";

    }
}
