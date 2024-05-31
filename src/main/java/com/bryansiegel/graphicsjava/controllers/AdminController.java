package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.impls.indexOfFormsRepositoryImpl;
import com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository;
import com.bryansiegel.graphicsjava.repositories.formDownloadsRepository;
import com.bryansiegel.graphicsjava.repositories.indexOfFormsRepository;
import com.bryansiegel.graphicsjava.repositories.siteBasedContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class AdminController {

    private final indexOfFormsRepository indexOfFormsRepository;
    private final formDownloadsRepository downloadsRepository;
    private final siteBasedContractsRepository siteBasedContractsRepository;
    private final currentEvaluationsRepository currentEvaluationsRepository;
    private final indexOfFormsRepositoryImpl indexOfFormsRepositoryImpl;

    @Autowired
    public AdminController(com.bryansiegel.graphicsjava.repositories.indexOfFormsRepository indexOfFormsRepository, formDownloadsRepository downloadsRepository, com.bryansiegel.graphicsjava.repositories.siteBasedContractsRepository siteBasedContractsRepository, com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository currentEvaluationsRepository, com.bryansiegel.graphicsjava.impls.indexOfFormsRepositoryImpl indexOfFormsRepositoryImpl) {
        this.indexOfFormsRepository = indexOfFormsRepository;
        this.downloadsRepository = downloadsRepository;
        this.siteBasedContractsRepository = siteBasedContractsRepository;
        this.currentEvaluationsRepository = currentEvaluationsRepository;
        this.indexOfFormsRepositoryImpl = indexOfFormsRepositoryImpl;
    }


    @GetMapping("/login")
    String login() {
        return "admin/login";
    }

    @GetMapping("/admin/dashboard")
    String dashboard(Model model) {
//        model.addAttribute("indexofforms", indexOfFormsRepositoryImpl.latestFiveResults(5));
        Pageable limit = PageRequest.of(0,3);
        model.addAttribute("indexofforms", indexOfFormsRepository.findAll(limit));

        return "admin/dashboard";
    }
}
