package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.dtos.CurrentEvaluationsDto;
import com.bryansiegel.graphicsjava.models.CurrentEvaluationsModel;
import com.bryansiegel.graphicsjava.models.IndexOfFormsModel;
import com.bryansiegel.graphicsjava.models.SiteBasedContractsModel;
import com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;


@Controller
public class CurrentEvaluationsController {
    //file upload dir
    String UPLOAD_DIR = "src/main/resources/static/files/current-evaluations/";

    @Autowired
    private final currentEvaluationsRepository currentEvaluationsRepository;


    public CurrentEvaluationsController(currentEvaluationsRepository currentEvaluationsRepository) {
        this.currentEvaluationsRepository = currentEvaluationsRepository;
    }


    //index
    @GetMapping("/admin/current-evaluations/")
    public String currentEvaluations(Model model) {
        model.addAttribute("currentEvaluations", currentEvaluationsRepository.findAll());
        return "admin/current-evaluations/index.html";
    }

    //GET create
    @GetMapping("admin/current-evaluations/create")
    public String showCreatePage(Model model) {
        CurrentEvaluationsDto currentEvaluationDto = new CurrentEvaluationsDto();
        model.addAttribute("currentEvaluationDto", currentEvaluationDto);
        return "admin/current-evaluations/create.html";
    }

    //POST create
    @PostMapping("/admin/current-evaluations/create")
    public String createCurrentEvaluation(@Valid @ModelAttribute CurrentEvaluationsDto currentEvaluationDto, @RequestParam String formName, CurrentEvaluationsModel _currentEvaluationsModel, BindingResult result) {

        if (currentEvaluationDto.getFile().isEmpty()) {
            result.addError(new FieldError("currentEvaluationDto", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/current-evaluations/create.html";
        }

        //save file
        MultipartFile file = currentEvaluationDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


        //SET FilePath
        String filePath = "files/current-evaluations/" + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                //Save to db
                _currentEvaluationsModel = new CurrentEvaluationsModel();
                _currentEvaluationsModel.setFormName(formName);
                _currentEvaluationsModel.setFilePath(filePath);

                currentEvaluationsRepository.save(_currentEvaluationsModel);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/current-evaluations/";
    }

    //Edit
    @GetMapping("/admin/current-evaluations/edit/{id}")
    public String editCurrentEvaluations(@PathVariable Long id, Model model) {
        CurrentEvaluationsModel currentEvaluations = currentEvaluationsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("currentEvaluations", currentEvaluations);

        return "admin/current-evaluations/edit.html";
    }

    //Update
    @PostMapping("/admin/current-evaluations/update/{id}")
    public String updateCurrentEvaluations(@Valid @ModelAttribute CurrentEvaluationsDto currentEvaluationDto, @PathVariable Long id, @RequestParam String formName,@RequestParam("file") MultipartFile file, CurrentEvaluationsModel _currentEvaluationsModel, BindingResult result) {


        if (result.hasErrors()) {
            return "admin/current-evaluations/edit.html";
        }

        Optional<CurrentEvaluationsModel> optionalCurrentEvaluationsModel = currentEvaluationsRepository.findById(id);

        //no file change on edit
        if (optionalCurrentEvaluationsModel.isPresent() && !currentEvaluationDto.getFile().isEmpty()) {
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();

            //SET FilePath
            String filePath = "files/current-evaluations/" + storageFileName;

            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                    //Save to db
                    CurrentEvaluationsModel currentEvaluationsModel = optionalCurrentEvaluationsModel.get();
                    currentEvaluationsModel.setFormName(formName);
                    currentEvaluationsModel.setFilePath(filePath);

                    currentEvaluationsRepository.save(currentEvaluationsModel);
                }

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

        } else if(optionalCurrentEvaluationsModel.isPresent()) {
            //Save to db
            CurrentEvaluationsModel currentEvaluationsModel = optionalCurrentEvaluationsModel.get();
            currentEvaluationsModel.setFormName(formName);

            currentEvaluationsRepository.save(currentEvaluationsModel);
        }


        return "redirect:/admin/current-evaluations/";
    }

    //Delete
    @GetMapping("/admin/current-evaluations/delete/{id}")
    public String deleteCurrentEvaluations(@PathVariable Long id) {
        CurrentEvaluationsModel currentEvaluations = currentEvaluationsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        currentEvaluationsRepository.delete(currentEvaluations);

        return "redirect:/admin/current-evaluations/";
    }
}
