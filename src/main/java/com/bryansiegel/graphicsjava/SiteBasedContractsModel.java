package com.bryansiegel.graphicsjava;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class SiteBasedContractsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Form Name is Required")
    private String FormName;

    private String FormUpload;

    private String FormUrl;

    private boolean active = true;
}
