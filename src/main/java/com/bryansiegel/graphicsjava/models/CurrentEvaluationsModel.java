package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class CurrentEvaluationsModel {
    public CurrentEvaluationsModel() {
    }

    public CurrentEvaluationsModel(Long id, String formName, String formUpload, String formUrl, boolean active) {
        this.id = id;
        FormName = formName;
        FormUpload = formUpload;
        FormUrl = formUrl;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Form Name is Required")
    private String FormName;

    private String FormUpload;

    private String FormUrl;

    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormName() {
        return FormName;
    }

    public void setFormName(String formName) {
        FormName = formName;
    }

    public String getFormUpload() {
        return FormUpload;
    }

    public void setFormUpload(String formUpload) {
        FormUpload = formUpload;
    }

    public String getFormUrl() {
        return FormUrl;
    }

    public void setFormUrl(String formUrl) {
        FormUrl = formUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CurrentEvaluationsModel{" +
                "id=" + id +
                ", FormName='" + FormName + '\'' +
                ", FormUpload='" + FormUpload + '\'' +
                ", FormUrl='" + FormUrl + '\'' +
                ", active=" + active +
                '}';
    }
}
