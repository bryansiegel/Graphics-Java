package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class DownloadsModel {

    public DownloadsModel() {
    }

    public DownloadsModel(long id, String category, String formName, String filePath) {
        this.id = id;
        Category = category;
        FormName = formName;
        FilePath = filePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Category is Required")
    private String Category;

    @NotBlank(message = "Form Name is Required")
    private String FormName;

    private String FilePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Category is Required") String getCategory() {
        return Category;
    }

    public void setCategory(@NotBlank(message = "Category is Required") String category) {
        Category = category;
    }

    public @NotBlank(message = "Form Name is Required") String getFormName() {
        return FormName;
    }

    public void setFormName(@NotBlank(message = "Form Name is Required") String formName) {
        FormName = formName;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    @Override
    public String toString() {
        return "DownloadsModel{" +
                "id=" + id +
                ", Category='" + Category + '\'' +
                ", FormName='" + FormName + '\'' +
                ", FilePath='" + FilePath + '\'' +
                '}';
    }
}
