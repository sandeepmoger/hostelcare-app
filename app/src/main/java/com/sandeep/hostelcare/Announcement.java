package com.sandeep.hostelcare;

// Announcement.java
public class Announcement {
    private String announcementText;
    private long timestamp;

    // Required default constructor for Firebase
    public Announcement() {
    }

    public Announcement(String announcementText, long timestamp) {
        this.announcementText = announcementText;
        this.timestamp = timestamp;
    }

    public String getAnnouncementText() {
        return announcementText;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
