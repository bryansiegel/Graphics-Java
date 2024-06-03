package com.bryansiegel.graphicsjava.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class SchoolLogosDto {

    public SchoolLogosDto() {
    }

    public SchoolLogosDto(Long id, String formName, String filePath, String category, MultipartFile file, boolean active) {
        this.id = id;
        FormName = formName;
        FilePath = filePath;
        Category = category;
        File = file;
        this.active = active;
    }


    private Long id;

    @NotBlank(message = "Form Name Cannot Be Blank")
    private String FormName;

    private String FilePath;

    private String Category;

    private MultipartFile File;

    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Form Name Cannot Be Blank") String getFormName() {
        return FormName;
    }

    public void setFormName(@NotBlank(message = "Form Name Cannot Be Blank") String formName) {
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

    public MultipartFile getFile() {
        return File;
    }

    public void setFile(MultipartFile file) {
        File = file;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SchoolLogosDto{" +
                "id=" + id +
                ", FormName='" + FormName + '\'' +
                ", FilePath='" + FilePath + '\'' +
                ", Category='" + Category + '\'' +
                ", File=" + File +
                ", active=" + active +
                '}';
    }
}
