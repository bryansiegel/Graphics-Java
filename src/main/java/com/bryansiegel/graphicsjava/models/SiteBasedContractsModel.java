package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class SiteBasedContractsModel {

    public SiteBasedContractsModel() {
    }

    public SiteBasedContractsModel(Long id, String formName, String filePath, boolean active) {
        this.id = id;
        FormName = formName;
        FilePath = filePath;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Form Name is Required")
    private String FormName;

    private String FilePath;

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

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SiteBasedContractsModel{" +
                "id=" + id +
                ", FormName='" + FormName + '\'' +
                ", FilePath='" + FilePath + '\'' +
                ", active=" + active +
                '}';
    }
}
