package com.ouieat.models;

public class Address {

    private String streetAddress;
    private String city;
    private String province;

    private String zipCode;

    public Address(
        String streetAddress,
        String city,
        String province,
        String zipCode
    ) {
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.streetAddress = streetAddress;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
