package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class PagesController {


    private final indexOfFormsRepository _indexofFormsRepository;
    private final formDownloadsRepository _formDownloadsRepository;
    private final siteBasedContractsRepository _siteBasedContractsRepository;
    private final currentEvaluationsRepository _currentEvaluationsRepository;
    private final downloadsRepository _downloadsRepository;
    private final schoolLogosRepository _schoolLogosRepository;
    private final workRequestsRepository _workRequestRepository;

    @Autowired
    public PagesController(indexOfFormsRepository _indexofFormsRepository, formDownloadsRepository _formDownloadsRepository, siteBasedContractsRepository _siteBasedContractsRepository, currentEvaluationsRepository _currentEvaluationsRepository, downloadsRepository downloadsRepository, schoolLogosRepository schoolLogosRepository, workRequestsRepository workRequestRepository) {
        this._indexofFormsRepository = _indexofFormsRepository;
        this._formDownloadsRepository = _formDownloadsRepository;
        this._siteBasedContractsRepository = _siteBasedContractsRepository;
        this._currentEvaluationsRepository = _currentEvaluationsRepository;
        _downloadsRepository = downloadsRepository;
        _schoolLogosRepository = schoolLogosRepository;
        _workRequestRepository = workRequestRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("workRequests", _workRequestRepository.findAll());
        return "public/home";
    }

    @GetMapping("/forms-archives")
    public String formsArchives(Model model) {
        model.addAttribute("indexOfForms", _indexofFormsRepository.findAll());
        model.addAttribute("formDownloads", _formDownloadsRepository.findAll());
        model.addAttribute("siteBasedContracts", _siteBasedContractsRepository.findAll());
        model.addAttribute("currentEvaluations", _currentEvaluationsRepository.findAll());
        model.addAttribute("workrequests", _workRequestRepository.findAll());
        return "public/forms-archives";
    }

    @GetMapping("/templates-logos")
    public String templatesLogos(Model model) {
        model.addAttribute("downloads", _downloadsRepository.findAll());
        model.addAttribute("workrequests", _workRequestRepository.findAll());
        return "public/templates-logos";
    }

    /* School Logos */

    //Elementary Schools
    @GetMapping("/elementary-school-logos")
    public String elementarySchoolLogos(Model model) {

        //get current main url
        String currentUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";
        model.addAttribute("elementarySchoolLogos", _schoolLogosRepository.findAll());
        model.addAttribute("currentUrl", currentUrl);
        return "public/elementary-school-logos";
    }

    //Middle Schools
    @GetMapping("/middle-school-logos")
    public String middleSchoolLogos(Model model)
    {
        //get current main url
        String currentUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";

        model.addAttribute("middleSchoolLogos", _schoolLogosRepository.findAll());
        model.addAttribute("currentUrl", currentUrl);
        return "public/middle-school-logos";
    }

    //High Schools
    @GetMapping("/high-school-logos")
    public String highSchoolLogos(Model model)
    {
        //get current main url
        String currentUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";

        model.addAttribute("highSchoolLogos", _schoolLogosRepository.findAll());
        model.addAttribute("currentUrl", currentUrl);
        return "public/high-school-logos";
    }

}
