package com.jsa.analytics.model;

public class MessageModel {
    String message;
    String date_time;
    Boolean isAdmin;

    public MessageModel(String message, String date_time, Boolean isAdmin) {
        this.message = message;
        this.date_time = date_time;
        this.isAdmin = isAdmin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
