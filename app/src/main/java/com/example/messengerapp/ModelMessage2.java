package com.example.messengerapp;

public class ModelMessage2 {
    String id, message, uid, userType;
    String timestamp;

    public ModelMessage2() {
    }

    public ModelMessage2(String id, String message, String uid, String userType, String timestamp) {
        this.id = id;
        this.message = message;
        this.uid = uid;
        this.userType = userType;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
