package com.example.messengerapp;

public class ModelMessage {
    String id, message, uid;
    String timestamp;

    public ModelMessage() {

    }

    public ModelMessage(String id, String message, String uid, String  timestamp) {
        this.id = id;
        this.message = message;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
