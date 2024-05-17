package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.models.CurrentEvaluationsModel;
import com.bryansiegel.graphicsjava.repositories.currentEvaluationsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class CurrentEvaluationsController {

    private final currentEvaluationsRepository currentEvaluationsRepository;

    @Autowired
    public CurrentEvaluationsController(currentEvaluationsRepository currentEvaluationsRepository) {
        this.currentEvaluationsRepository = currentEvaluationsRepository;
    }

    //index
    @GetMapping("/admin/current-evaluations/")
    public String currentEvaluations(Model model) {
        model.addAttribute("currentEvaluations", currentEvaluationsRepository.findAll());
        return "admin/current-evaluations/index.html";
    }

    //create
    @GetMapping("admin/current-evaluations/create")
    public String create(CurrentEvaluationsModel model) {
        return "admin/current-evaluations/create.html";
    }

    //create
    @PostMapping("/admin/current-evaluations/create")
    public String createCurrentEvaluations(@Valid CurrentEvaluationsModel currentEvaluations, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/current-evaluations/create.html";
        }

        currentEvaluationsRepository.save(currentEvaluations);
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
    public String updateCurrentEvaluations(@Valid CurrentEvaluationsModel currentEvaluations, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/current-evaluations/edit.html";
        }
        currentEvaluationsRepository.save(currentEvaluations);
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
