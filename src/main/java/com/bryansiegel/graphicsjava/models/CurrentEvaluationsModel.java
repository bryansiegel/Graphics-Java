package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.*;

@Entity
public class CurrentEvaluationsModel {
    public CurrentEvaluationsModel() {
    }

    public CurrentEvaluationsModel(Long id, String formName, String fileName, boolean active) {
        this.id = id;
        FormName = formName;
        FileName = fileName;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FormName;

    private String FileName;


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

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
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
                ", FilePath='" + FileName + '\'' +
                ", active=" + active +
                '}';
    }
}
