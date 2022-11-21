package com.ouieat.models;

public class Address {

    private String streetName;
    private String city;
    private String province;

    private String zipCode;

    public Address(
        String streetName,
        String city,
        String province,
        String zipCode
    ) {
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.streetName = streetName;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
