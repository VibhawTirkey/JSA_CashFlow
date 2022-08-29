package com.jsa.analytics.model;

public class SignupUserModel {
    String createdDate;
    String email;
    String exclusiveMode;
    Boolean isExclusiveMember;
    String name;
    String phone;

    public SignupUserModel() {
    }

    public SignupUserModel(String createdDate, String email, String exclusiveMode, Boolean isExclusiveMember, String name, String phone) {
        this.createdDate = createdDate;
        this.email = email;
        this.exclusiveMode = exclusiveMode;
        this.isExclusiveMember = isExclusiveMember;
        this.name = name;
        this.phone = phone;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExclusiveMode() {
        return exclusiveMode;
    }

    public void setExclusiveMode(String exclusiveMode) {
        this.exclusiveMode = exclusiveMode;
    }

    public Boolean getExclusiveMember() {
        return isExclusiveMember;
    }

    public void setExclusiveMember(Boolean exclusiveMember) {
        isExclusiveMember = exclusiveMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
