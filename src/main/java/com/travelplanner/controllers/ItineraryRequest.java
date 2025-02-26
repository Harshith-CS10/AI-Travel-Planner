package com.travelplanner.controllers;

public class ItineraryRequest {
    private String destination;
    private int days;

    // Default Constructor
    public ItineraryRequest() {}

    // Getters and setters
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
