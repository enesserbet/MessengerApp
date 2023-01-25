package com.example.messengerapp;

public class ModelMessage {
    String id, message, uid;
    long timestamp;

    public ModelMessage() {

    }

    public ModelMessage(String id, String message, String uid, long timestamp) {
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
