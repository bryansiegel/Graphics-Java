package com.bryansiegel.graphicsjava.dtos;

import org.springframework.web.multipart.MultipartFile;

public class GACWorkRequestDto {
    private String full_name;
    private String email;
    private String phone;
    private String location_department;
    private String project_name;
    private String google_drive_link;
    private String message;

    private MultipartFile GAC_work_request_form;
    private MultipartFile[] file_uploads;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation_department() {
        return location_department;
    }

    public void setLocation_department(String location_department) {
        this.location_department = location_department;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getGoogle_drive_link() {
        return google_drive_link;
    }

    public void setGoogle_drive_link(String google_drive_link) {
        this.google_drive_link = google_drive_link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultipartFile getGAC_work_request_form() {
        return GAC_work_request_form;
    }

    public void setGAC_work_request_form(MultipartFile GAC_work_request_form) {
        this.GAC_work_request_form = GAC_work_request_form;
    }

    public MultipartFile[] getFile_uploads() {
        return file_uploads;
    }

    public void setFile_uploads(MultipartFile[] file_uploads) {
        this.file_uploads = file_uploads;
    }
}