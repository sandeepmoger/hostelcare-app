package com.sandeep.hostelcare;

public class User {
    private String username;
    private String mobileNumber;

    // Required default constructor for Firebase
    public User() {
    }

    public User(String username, String mobileNumber) {
        this.username = username;
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
