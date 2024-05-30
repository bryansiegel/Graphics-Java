package com.bryansiegel.graphicsjava.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class IndexOfFormsDto {

    public IndexOfFormsDto() {
    }

    public IndexOfFormsDto(String formName, MultipartFile file) {
        FormName = formName;
        File = file;
    }

    @NotBlank(message = "Form Name Cannot Be Blank")
    private String FormName;

    private MultipartFile File;

    public String getFormName() {
        return FormName;
    }

    public void setFormName(String formName) {
        FormName = formName;
    }

    public MultipartFile getFile() {
        return File;
    }

    public void setFile(MultipartFile file) {
        File = file;
    }
}
