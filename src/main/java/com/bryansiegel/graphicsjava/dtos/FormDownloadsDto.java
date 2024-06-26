package com.bryansiegel.graphicsjava.dtos;

import org.springframework.web.multipart.MultipartFile;

public class FormDownloadsDto {

    public FormDownloadsDto() {
    }

    public FormDownloadsDto(String formType, String formNumber, String formName, MultipartFile file) {
        FormType = formType;
        FormNumber = formNumber;
        FormName = formName;
        File = file;
    }

    private String FormType;
    private String FormNumber;
    private String FormName;
    private String FormMessage;
    private MultipartFile File;

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

    public String getFormMessage() {
        return FormMessage;
    }

    public void setFormMessage(String formMessage) {
        FormMessage = formMessage;
    }

    public MultipartFile getFile() {
        return File;
    }

    public void setFile(MultipartFile file) {
        File = file;
    }
}
