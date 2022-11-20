package com.ouieat.models;

import java.lang.reflect.Array;

public class Filter {

    // distance of restaurant from current location
    private float distance;

    private float userLatitude;

    private float userLongitude;

    // type of food (ex - Indian, Chinese, Italian etc.)
    private String[] cuisine;

    // contains 2 values- $min and $max
    private int priceRangeMin;

    private int priceRangeMax;

    public Filter(
        float distance,
        float userLatitude,
        float userLongitude,
        String[] cuisine,
        int priceRangeMin,
        int priceRangeMax
    ) {
        this.distance = distance;
        this.userLatitude = userLatitude;
        this.userLongitude = userLatitude;
        this.cuisine = cuisine;
        this.priceRangeMin = priceRangeMin;
        this.priceRangeMax = priceRangeMax;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setCuisine(String[] cuisine) {
        this.cuisine = cuisine;
    }

    public String[] getCuisine() {
        return cuisine;
    }

    public void setUserLatitude(int userLatitude) {
        this.userLatitude = userLatitude;
    }

    public float getUserLatitude() {
        return userLatitude;
    }

    public void setUserLongitude(int userLongitude) {
        this.userLongitude = userLongitude;
    }

    public float getUserLongitude() {
        return userLongitude;
    }

    public void setPriceRangeMin(int priceRangeMin) {
        this.priceRangeMin = priceRangeMin;
    }

    public int getPriceRangeMin() {
        return priceRangeMin;
    }

    public void setPriceRangeMax(int priceRangeMax) {
        this.priceRangeMax = priceRangeMax;
    }

    public int getPriceRangeMax() {
        return priceRangeMax;
    }
}
