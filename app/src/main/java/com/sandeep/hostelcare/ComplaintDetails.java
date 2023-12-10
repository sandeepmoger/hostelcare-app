package com.sandeep.hostelcare;

public class ComplaintDetails {
    private String name;
    private String complaintText;
    private String block;

    //getters

    public String getName() {
        return name;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public String getBlock() {
        return block;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    private String roomNumber;

    public ComplaintDetails() {
        // Default constructor required for Firebase
    }

    public ComplaintDetails(String name, String complaintText, String block, String roomNumber) {
        this.name = name;
        this.complaintText = complaintText;
        this.block = block;
        this.roomNumber = roomNumber;
    }



    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
