package com.bryansiegel.graphicsjava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;

@Entity
public class CurrentEvaluationsModel {
    public CurrentEvaluationsModel() {
    }

    public CurrentEvaluationsModel(Long id, String formName, byte[] data, String formUrl, String type, String name, boolean active) {
        this.id = id;
        FormName = formName;
        this.data = data;
        FormUrl = formUrl;
        this.type = type;
        this.name = name;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Form Name is Required")
    private String FormName;


    @Lob
    private byte[] data;

    private String FormUrl;

    private String FormUpload;

    private String type;
    private String name;


    private boolean active = true;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CurrentEvaluationsModel{" +
                "id=" + id +
                ", FormName='" + FormName + '\'' +
                ", data=" + Arrays.toString(data) +
                ", FormUrl='" + FormUrl + '\'' +
                ", FormUpload='" + FormUpload + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
