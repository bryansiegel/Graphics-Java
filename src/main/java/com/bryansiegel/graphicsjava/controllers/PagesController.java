package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class PagesController {

    private final indexOfFormsRepository _indexofFormsRepository;
    private final formDownloadsRepository _formDownloadsRepository;
    private final siteBasedContractsRepository _siteBasedContractsRepository;
    private final currentEvaluationsRepository _currentEvaluationsRepository;
    private final downloadsRepository _downloadsRepository;

    @Autowired
    public PagesController(indexOfFormsRepository _indexofFormsRepository, formDownloadsRepository _formDownloadsRepository, siteBasedContractsRepository _siteBasedContractsRepository, currentEvaluationsRepository _currentEvaluationsRepository, downloadsRepository downloadsRepository) {
        this._indexofFormsRepository = _indexofFormsRepository;
        this._formDownloadsRepository = _formDownloadsRepository;
        this._siteBasedContractsRepository = _siteBasedContractsRepository;
        this._currentEvaluationsRepository = _currentEvaluationsRepository;
        _downloadsRepository = downloadsRepository;
    }

    @GetMapping("/")
    public String home() {
        return "public/home";
    }

    @GetMapping("/forms-archives")
    public String formsArchives(Model model)
    {
        model.addAttribute("indexOfForms", _indexofFormsRepository.findAll());
        model.addAttribute("formDownloads", _formDownloadsRepository.findAll());
        model.addAttribute("siteBasedContracts", _siteBasedContractsRepository.findAll());
        model.addAttribute("currentEvaluations", _currentEvaluationsRepository.findAll());
        return "public/forms-archives";
    }

    @GetMapping("/templates-logos")
    public String templatesLogos(Model model)
    {
        model.addAttribute("downloads", _downloadsRepository.findAll());
        return "public/templates-logos";
    }

    //School Logos
    //Elementary Schools
    @GetMapping("public/elementary-school-logos")
    public String elementarySchoolLogos()
    {

        return "public/elementarySchoolLogos";
    }
}
