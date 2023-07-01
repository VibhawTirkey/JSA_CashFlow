package com.jsa.analytics.model;

import com.google.firebase.Timestamp;

public class MessageModel {
    String message;
    Timestamp createdOn;
    Boolean isAdmin;
    String isSeen;

    public MessageModel() {
    }

    public MessageModel(String message, Timestamp createdOn, Boolean isAdmin, String isSeen) {
        this.message = message;
        this.createdOn = createdOn;
        this.isAdmin = isAdmin;
        this.isSeen = isSeen;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(String isSeen) {
        this.isSeen = isSeen;
    }
}
