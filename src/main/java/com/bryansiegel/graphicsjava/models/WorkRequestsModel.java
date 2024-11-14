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

//    @NotBlank(message ="Full Name is Required")
    private String FullName;

//    @NotBlank(message = "Email Address is Required")
    private String EmailAddress;

//    @NotBlank(message = "Phone Number is Required")
    private String PhoneNumber;

//    @NotBlank(message = "Location/Department Name is Required")
    private String LocationDepartmentName;

//    @NotBlank(message = "Project Name is Required")
    private String ProjectName;

//    @NotBlank(message = "Google Drive Link is Required")
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getLocationDepartmentName() {
        return LocationDepartmentName;
    }

    public void setLocationDepartmentName(String locationDepartmentName) {
        LocationDepartmentName = locationDepartmentName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
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
