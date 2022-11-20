package com.ouieat.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurant")
public class Restaurant {

    private String address;
    private float longitude;
    private float latitude;
    private String cuisine;

    private String name;

    public Restaurant(
        float latitude,
        float longitude,
        String address,
        String cuisine,
        String name
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.cuisine = cuisine;
        this.name = name;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisine() {
        return this.cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
