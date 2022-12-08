package com.ouieat.models.restaurant;

import com.ouieat.models.Model;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
public class Restaurant extends Model {

    private String alias;

    private String name;

    private String image_url;

    private String url;

    private int review_count;

    private Category[] categories;

    private double rating;

    private Coordinates coordinates;

    private String price;

    private Location location;

    private String phone;

    private String display_phone;

    public Restaurant(
        String alias,
        String name,
        String image_url,
        String url,
        int review_count,
        Category[] categories,
        double rating,
        Coordinates coordinates,
        String price,
        Location location,
        String phone,
        String display_phone
    ) {
        this.alias = alias;
        this.name = name;
        this.image_url = image_url;
        this.url = url;
        this.review_count = review_count;
        this.categories = categories;
        this.rating = rating;
        this.coordinates = coordinates;
        this.price = price;
        this.location = location;
        this.phone = phone;
        this.display_phone = display_phone;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }
}
