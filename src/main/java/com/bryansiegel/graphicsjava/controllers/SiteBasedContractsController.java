package com.bryansiegel.graphicsjava.controllers;



import com.bryansiegel.graphicsjava.models.SiteBasedContractsDto;
import com.bryansiegel.graphicsjava.models.SiteBasedContractsModel;
import com.bryansiegel.graphicsjava.repositories.siteBasedContractsRepository;
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
public class SiteBasedContractsController {

    //file upload dir
    String UPLOAD_DIR = "src/main/resources/static/files/site-based-contracts/";

    @Autowired
    private final siteBasedContractsRepository repo;

    public SiteBasedContractsController(siteBasedContractsRepository repo) {
        this.repo = repo;
    }

    //index
    @GetMapping("/admin/site-based-contracts/")
    public String siteBasedContracts(Model model) {
        model.addAttribute("sitebasedcontracts", repo.findAll());
        return "admin/site-based-contracts/index.html";
    }

    //GET create
    @GetMapping("admin/site-based-contracts/create")
    public String showCreatePage(Model model) {
        SiteBasedContractsDto siteBasedContractsDto = new SiteBasedContractsDto();
        model.addAttribute("siteBasedContractsDto", siteBasedContractsDto);
        return "admin/site-based-contracts/create.html";
    }

    //POST create
    @PostMapping("/admin/site-based-contracts/create")
    public String createSiteBasedContract(@Valid @ModelAttribute SiteBasedContractsDto siteBasedContractsDto, @RequestParam String formName, SiteBasedContractsModel _siteBasedContractsModel, BindingResult result) {

        if (siteBasedContractsDto.getFile().isEmpty()) {
            result.addError(new FieldError("sitebasedcontracts", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/site-based-contracts/create.html";
        }

        //save file
        MultipartFile file = siteBasedContractsDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


        //SET FilePath
        String filePath = "files/site-based-contracts/" + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                //Save to db
                _siteBasedContractsModel = new SiteBasedContractsModel();
                _siteBasedContractsModel.setFormName(formName);
                _siteBasedContractsModel.setFilePath(filePath);

                repo.save(_siteBasedContractsModel);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/site-based-contracts/";
    }

    //Edit
    @GetMapping("/admin/site-based-contracts/edit/{id}")
    public String editSiteBasedContracts(@PathVariable Long id, Model model) {

        SiteBasedContractsModel siteBasedContracts = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("siteBasedContracts", siteBasedContracts);

        return "admin/site-based-contracts/edit.html";
    }

    //Update
    @PostMapping("/admin/site-based-contracts/update/{id}")
    public String updateSiteBasedContracts(@Valid @ModelAttribute SiteBasedContractsDto siteBasedContractsDto, @PathVariable Long id, @RequestParam String formName, SiteBasedContractsModel _siteBasedContractsModel, BindingResult result) {

        if (siteBasedContractsDto.getFile().isEmpty()) {
            result.addError(new FieldError("siteBasedContractsDto", "file", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "admin/site-based-contracts/edit.html";
        }

        //save file
        MultipartFile file = siteBasedContractsDto.getFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + file.getOriginalFilename();


        //SET FilePath
        String filePath = "files/site-based-contracts/" + storageFileName;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(UPLOAD_DIR + storageFileName), StandardCopyOption.REPLACE_EXISTING);

                //Save to db
                SiteBasedContractsModel _sitebasedcontracts = repo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


                _sitebasedcontracts.setFormName(formName);
                _sitebasedcontracts.setFilePath(filePath);

                repo.save(_sitebasedcontracts);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/admin/site-based-contracts/";
    }

    //Delete
    @GetMapping("/admin/site-based-contracts/delete/{id}")
    public String deleteSiteBasedContracts(@PathVariable Long id) {
        SiteBasedContractsModel sitebasedcontracts = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(sitebasedcontracts);
        return "redirect:/admin/site-based-contracts/";
    }

}
