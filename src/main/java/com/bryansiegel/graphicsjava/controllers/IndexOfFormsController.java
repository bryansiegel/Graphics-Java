package com.bryansiegel.graphicsjava.controllers;

import com.bryansiegel.graphicsjava.repositories.indexOfFormsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IndexOfFormsController {

//file upload dir
    String UPLOAD_DIR = "src/main/resources/static/files/index-of-forms/";

    @Autowired
    private final indexOfFormsRepository repo;

    public IndexOfFormsController(indexOfFormsRepository repo) {
        this.repo = repo;
    }


}
