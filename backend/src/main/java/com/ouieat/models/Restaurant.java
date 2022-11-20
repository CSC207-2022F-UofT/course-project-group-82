package com.ouieat.models;

import java.time.LocalDateTime;

public class Restaurant {

    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private Address address;
    private String cuisine;

    private String name;

    public Restaurant(
        LocalDateTime openTime,
        LocalDateTime closeTime,
        Address address,
        String cuisine,
        String name
    ) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.cuisine = cuisine;
        this.name = name;
    }

    public LocalDateTime getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
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
