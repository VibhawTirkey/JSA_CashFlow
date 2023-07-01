package com.jsa.analytics.model;

import com.google.firebase.Timestamp;

public class PostQuestionModel {
    String topic;
    String description;
    String status;
    Timestamp createdOn;
    Chats chats;

    public PostQuestionModel(String topic, String description, String status, Timestamp createdOn) {
        this.topic = topic;
        this.description = description;
        this.status = status;
        this.createdOn = createdOn;
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Chats getChats() {
        return chats;
    }

    public void setChats(Chats chats) {
        this.chats = chats;
    }

    public static class Chats{
        String message;
        String createdOn;
    }
}
