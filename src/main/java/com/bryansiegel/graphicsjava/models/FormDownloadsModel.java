package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class FormDownloadsModel {

    public FormDownloadsModel() {
    }

    public FormDownloadsModel(long id, String formType, String formNumber, String formName, String formUrl, String formUpload, boolean active) {
        this.id = id;
        FormType = formType;
        FormNumber = formNumber;
        FormName = formName;
        FormUrl = formUrl;
        FormUpload = formUpload;
        this.active = active;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormType() {
        return FormType;
    }

    public void setFormType(String formType) {
        FormType = formType;
    }

    public String getFormNumber() {
        return FormNumber;
    }

    public void setFormNumber(String formNumber) {
        FormNumber = formNumber;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "FormDownloadsModel{" +
                "id=" + id +
                ", FormType='" + FormType + '\'' +
                ", FormNumber='" + FormNumber + '\'' +
                ", FormName='" + FormName + '\'' +
                ", FormUrl='" + FormUrl + '\'' +
                ", FormUpload='" + FormUpload + '\'' +
                ", active=" + active +
                '}';
    }
}
