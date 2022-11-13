package com.ouieat.models;
import java.time.LocalDateTime;

public class Restaurant {

    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private Address address;
    private String cuisine;


    public Restaurant(LocalDateTime openTime, LocalDate closeTime, Address address, String cuisine) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.cuisine = cuisine;

    }

    public LocalDateTime getOpenTime () {
        return this.openTime;
    }

    public void setOpenTime(openTime) {  this.openTime = openTime;}

    public LocalDateTime getCloseTime () {
        return this.closeTime;
    }

    public void setCloseTime(closeTime) {  this.closeTime = closeTime;}

    public Address getAddress () {return this.address;}

    public void setAddress(address) {  this.address = address;}

    public String getCuisine () {
        return this.cuisine;
    }

    public void setCuisine(cuisine) {  this.cuisine = cuisine;}

}
