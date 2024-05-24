package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DownloadsDto {

    public DownloadsDto() {
    }

    public DownloadsDto(Long id, String formName, String filePath, String category, boolean active) {
        this.id = id;
        FormName = formName;
        FilePath = filePath;
        Category = category;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FormName;

    private String FilePath;

    private String Category;

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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
