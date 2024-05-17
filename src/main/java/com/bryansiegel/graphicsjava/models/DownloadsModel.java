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

    public DownloadsModel(long id, String category, String formName, String formUrl, String formUpload) {
        this.id = id;
        Category = category;
        FormName = formName;
        FormUrl = formUrl;
        FormUpload = formUpload;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Category is Required")
    private String Category;

    @NotBlank(message = "Form Name is Required")

    private String FormName;

    private String FormUrl;

    private String FormUpload;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getFormName() {
        return FormName;
    }

    public void setFormName(String formName) {
        FormName = formName;
    }

    public String getFormUrl() {
        return FormUrl;
    }

    public void setFormUrl(String formUrl) {
        FormUrl = formUrl;
    }

    public String getFormUpload() {
        return FormUpload;
    }

    public void setFormUpload(String formUpload) {
        FormUpload = formUpload;
    }

    @Override
    public String toString() {
        return "DownloadsModel{" +
                "id=" + id +
                ", Category='" + Category + '\'' +
                ", FormName='" + FormName + '\'' +
                ", FormUrl='" + FormUrl + '\'' +
                ", FormUpload='" + FormUpload + '\'' +
                '}';
    }
}
