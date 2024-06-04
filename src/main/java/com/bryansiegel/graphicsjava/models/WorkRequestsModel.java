package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class WorkRequestsModel {

    public WorkRequestsModel() {
    }

    public WorkRequestsModel(Long id, String fullName, String emailAddress, String phoneNumber, String locationDepartmentName, String projectName, String googleDriveLink, String message, String filePath, boolean active) {
        this.id = id;
        FullName = fullName;
        EmailAddress = emailAddress;
        PhoneNumber = phoneNumber;
        LocationDepartmentName = locationDepartmentName;
        ProjectName = projectName;
        GoogleDriveLink = googleDriveLink;
        Message = message;
        FilePath = filePath;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @NotBlank(message = "Google Drive Link is Required")
    @Column(columnDefinition = "TEXT")
    private String GoogleDriveLink;

    @Column(columnDefinition = "TEXT")
    private String Message;

    private String FilePath;

    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public @NotBlank(message = "Google Drive Link is Required") String getGoogleDriveLink() {
        return GoogleDriveLink;
    }

    public void setGoogleDriveLink(@NotBlank(message = "Google Drive Link is Required") String googleDriveLink) {
        GoogleDriveLink = googleDriveLink;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
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
        return "WorkRequestsModel{" +
                "id=" + id +
                ", FullName='" + FullName + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", LocationDepartmentName='" + LocationDepartmentName + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", GoogleDriveLink='" + GoogleDriveLink + '\'' +
                ", Message='" + Message + '\'' +
                ", FilePath='" + FilePath + '\'' +
                ", active=" + active +
                '}';
    }
}
