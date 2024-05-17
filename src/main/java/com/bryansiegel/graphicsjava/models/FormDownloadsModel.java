package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class FormDownloadsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Form Type is Required")
    private String FormType;

    @NotBlank(message = "Form Number is Required")
    private String FormNumber;

    @NotBlank(message = "Form Name is Required")
    private String FormName;

    private String FormUrl;

    private String FormUpload;

    public boolean active = true;
}
