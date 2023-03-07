package com.jsa.analytics.model;

public class AskYourQuestionModel {
    String title;
    String description;
    String status;
    boolean isSeen;

    public AskYourQuestionModel() {
    }

    public AskYourQuestionModel(String title, String description, String status, boolean isSeen) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.isSeen = isSeen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
