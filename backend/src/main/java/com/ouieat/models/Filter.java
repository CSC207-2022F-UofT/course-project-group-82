package com.ouieat.models;

public class Filter {

    // distance of restaurant from current location
    private float distance;

    // type of food (ex - sushi, ramen, curry, pizza etc.)
    private String cuisine;

    // restaurant open/closed
    private String status;

    // yes or no
    private String waiting;

    private float ratings;

    // yes-if alcohol is served, no- if not
    private String alcohol;

    // dine-in, takeout
    private String serviceOption;

    public Filter(
        float distance,
        String cuisine,
        String status,
        String waiting,
        float ratings,
        String alcohol,
        String serviceOption
    ) {
        this.distance = distance;
        this.cuisine = cuisine;
        this.status = status;
        this.waiting = waiting;
        this.ratings = ratings;
        this.alcohol = alcohol;
        this.serviceOption = serviceOption;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }

    public String getWaiting() {
        return waiting;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public float getRatings() {
        return ratings;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setServiceOption(String serviceOption) {
        this.serviceOption = serviceOption;
    }

    public String getServiceOption(String serviceOption) {
        return serviceOption;
    }
}
