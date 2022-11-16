package com.ouieat.models;

import java.time.LocalDateTime;

public class Address {

    private String streetName;
    private int address;
    private String city;
    private String province;
    private String zipcode;
    private String country;
    private float latitude;
    private float longitude;

    public Address(
        String streetName,
        int address,
        String city,
        String province,
        String zipcode,
        String country,
        float latitude,
        float longitude
    ) {
        this.streetName = streetName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zipcode = zipcode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getAddress() {
        return this.address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
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
}
