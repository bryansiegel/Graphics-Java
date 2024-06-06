package com.bryansiegel.graphicsjava.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class WorkRequestsDto {

    public WorkRequestsDto() {
    }

    public WorkRequestsDto(Long id, String formName, String fullName, String emailAddress, String phoneNumber, String locationDepartmentName, String projectName, String googleDriveLink, String message, String filePath, MultipartFile file) {
        this.id = id;
        FormName = formName;
        FullName = fullName;
        EmailAddress = emailAddress;
        PhoneNumber = phoneNumber;
        LocationDepartmentName = locationDepartmentName;
        ProjectName = projectName;
        GoogleDriveLink = googleDriveLink;
        Message = message;
        this.filePath = filePath;
        File = file;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FormName;

    @NotBlank(message ="Full Name is Required")
    private String FullName;

    @NotBlank(message = "Email Address is Required")
    private String EmailAddress;

    @NotBlank(message = "Phone Number is Required")
    private String PhoneNumber;

    @NotBlank(message = "Location/Department Name is Required")
    private String LocationDepartmentName;

    @NotBlank(message = "Project Name is Required")
    private String ProjectName;

    @Column(columnDefinition = "TEXT")
    private String GoogleDriveLink;

    @Column(columnDefinition = "TEXT")
    private String Message;

    private String filePath;

    private MultipartFile File;

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

    public @NotBlank(message = "Full Name is Required") String getFullName() {
        return FullName;
    }

    public void setFullName(@NotBlank(message = "Full Name is Required") String fullName) {
        FullName = fullName;
    }

    public @NotBlank(message = "Email Address is Required") String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(@NotBlank(message = "Email Address is Required") String emailAddress) {
        EmailAddress = emailAddress;
    }

    public @NotBlank(message = "Phone Number is Required") String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone Number is Required") String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Location/Department Name is Required") String getLocationDepartmentName() {
        return LocationDepartmentName;
    }

    public void setLocationDepartmentName(@NotBlank(message = "Location/Department Name is Required") String locationDepartmentName) {
        LocationDepartmentName = locationDepartmentName;
    }

    public @NotBlank(message = "Project Name is Required") String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(@NotBlank(message = "Project Name is Required") String projectName) {
        ProjectName = projectName;
    }

    public String getGoogleDriveLink() {
        return GoogleDriveLink;
    }

    public void setGoogleDriveLink(String googleDriveLink) {
        GoogleDriveLink = googleDriveLink;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MultipartFile getFile() {
        return File;
    }

    public void setFile(MultipartFile file) {
        File = file;
    }
}
