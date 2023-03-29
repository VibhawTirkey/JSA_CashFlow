package com.jsa.analytics.model;

import com.google.firebase.Timestamp;

public class UsersModel {
    Timestamp createdDate;
    String email;
    Boolean exclusiveMember;
    String exclusiveMode;
    String name;
    String phone;

    public UsersModel() {
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getExclusiveMember() {
        return exclusiveMember;
    }

    public void setExclusiveMember(Boolean exclusiveMember) {
        this.exclusiveMember = exclusiveMember;
    }

    public String getExclusiveMode() {
        return exclusiveMode;
    }

    public void setExclusiveMode(String exclusiveMode) {
        this.exclusiveMode = exclusiveMode;
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
