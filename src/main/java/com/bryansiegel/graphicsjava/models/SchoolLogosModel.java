package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SchoolLogosModel {

    public SchoolLogosModel() {
    }

    public SchoolLogosModel(Long id, String formName, String category, String filePath, boolean active) {
        this.id = id;
        FormName = formName;
        Category = category;
        FilePath = filePath;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FormName;

    private String Category;

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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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
        return "SchoolLogosModel{" +
                "id=" + id +
                ", FormName='" + FormName + '\'' +
                ", Category='" + Category + '\'' +
                ", FilePath='" + FilePath + '\'' +
                ", active=" + active +
                '}';
    }
}
