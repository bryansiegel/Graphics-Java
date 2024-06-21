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

    public FormDownloadsModel(long id, String formType, String formNumber, String formName, String filePath, String formMessage, boolean active) {
        this.id = id;
        FormType = formType;
        FormNumber = formNumber;
        FormName = formName;
        FilePath = filePath;
        FormMessage = formMessage;
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

    private String FilePath;

    private String FormMessage;

    public boolean active = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Form Type is Required") String getFormType() {
        return FormType;
    }

    public void setFormType(@NotBlank(message = "Form Type is Required") String formType) {
        FormType = formType;
    }

    public @NotBlank(message = "Form Number is Required") String getFormNumber() {
        return FormNumber;
    }

    public void setFormNumber(@NotBlank(message = "Form Number is Required") String formNumber) {
        FormNumber = formNumber;
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

    public String getFormMessage() {
        return FormMessage;
    }

    public void setFormMessage(String formMessage) {
        FormMessage = formMessage;
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
                ", FilePath='" + FilePath + '\'' +
                ", FormMessage='" + FormMessage + '\'' +
                ", active=" + active +
                '}';
    }
}
