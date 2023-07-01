package com.jsa.analytics.model;

import com.google.firebase.Timestamp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatModel {
    String topic;
    String description;
    Timestamp createdOn;
    String status;
    ArrayList<MessageModel> chats;

    public ChatModel() {
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<MessageModel> getChats() {
        return chats;
    }

    public void setChats(ArrayList<MessageModel> chats) {
        this.chats = chats;
    }
}
