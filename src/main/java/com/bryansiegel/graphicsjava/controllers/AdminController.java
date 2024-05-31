package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class AdminController {

    Pageable limit = PageRequest.of(0,3);

    private final indexOfFormsRepository indexOfFormsRepository;
    private final formDownloadsRepository formdownloadsRepository;
    private final siteBasedContractsRepository siteBasedContractsRepository;
    private final currentEvaluationsRepository currentEvaluationsRepository;
    private final downloadsRepository downloadsRepository;

    @Autowired
    public AdminController(com.bryansiegel.graphicsjava.repositories.indexOfFormsRepository indexOfFormsRepository, formDownloadsRepository downloadsRepository, com.bryansiegel.graphicsjava.repositories.siteBasedContractsRepository siteBasedContractsRepository, com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository currentEvaluationsRepository, com.bryansiegel.graphicsjava.repositories.downloadsRepository downloadsRepository1) {
        this.indexOfFormsRepository = indexOfFormsRepository;
        this.formdownloadsRepository = downloadsRepository;
        this.siteBasedContractsRepository = siteBasedContractsRepository;
        this.currentEvaluationsRepository = currentEvaluationsRepository;
        this.downloadsRepository = downloadsRepository1;
    }


    @GetMapping("/login")
    String login() {
        return "admin/login";
    }

    @GetMapping("/admin/dashboard")
    String dashboard(Model model) {
        model.addAttribute("indexofforms", indexOfFormsRepository.findAll(limit));
        model.addAttribute("formdownloads", formdownloadsRepository.findAll(limit));
        model.addAttribute("sitebasedcontracts", siteBasedContractsRepository.findAll(limit));
        model.addAttribute("currentevaluations", currentEvaluationsRepository.findAll(limit));
        model.addAttribute("downloads", downloadsRepository.findAll(limit));

        return "admin/dashboard";
    }
}
