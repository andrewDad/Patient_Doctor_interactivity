package com.example.andy.patient_doctor_interactivity;

/**
 * Created by ANDY on 11/16/2017.
 */

public class Message {
    private String message,user_name;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public Message() {
    }
    public Message(String message, String user_name) {
        this.message = message;
        this.user_name = user_name;
    }
}
