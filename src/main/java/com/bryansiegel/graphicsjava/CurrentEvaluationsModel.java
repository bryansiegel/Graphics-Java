package com.bryansiegel.graphicsjava;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class CurrentEvaluationsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Form Name is Required")
    private String FormName;

    private String FormUpload;

    private String FormUrl;

    private boolean active = true;
}
