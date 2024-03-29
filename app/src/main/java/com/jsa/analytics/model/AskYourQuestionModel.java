package com.jsa.analytics.model;

public class AskYourQuestionModel {
    String topic;
    String description;
    String status;
    boolean isSeen;

    String documentId;

    public AskYourQuestionModel() {
    }

    public AskYourQuestionModel(String topic, String description, String status, boolean isSeen) {
        this.topic = topic;
        this.description = description;
        this.status = status;
        this.isSeen = isSeen;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
