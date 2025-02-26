package com.travelplanner.models;

public class ItineraryRequest {
    private String destination;
    private int days;

    // Constructors
    public ItineraryRequest() {}

    public ItineraryRequest(String destination, int days) {
        this.destination = destination;
        this.days = days;
    }

    // Getters and Setters
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
