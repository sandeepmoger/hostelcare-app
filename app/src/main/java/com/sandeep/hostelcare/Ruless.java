package com.sandeep.hostelcare;

public class Ruless {
    private String rulesText;
    private long timestamp;

    // Default constructor required for Firebase
    public Ruless() {
    }

    public Ruless(String rulesText, long timestamp) {
        this.rulesText = rulesText;
        this.timestamp = timestamp;
    }

    public String getRulesText() {
        return rulesText;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
