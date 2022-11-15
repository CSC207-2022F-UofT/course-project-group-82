package com.ouieat.models;
import java.time.LocalDateTime;

public class RestaurantRecommendation {

    private User user;
    private int rating;
    private Restaurant restaurant;
    private String review;
    private LocalDateTime postDate;

    public RestaurantRecommendation(User user, LocalDateTime postDate, int rating, Restaurant restaurant, String review) {
        this.user = user;
        this.restaurant = restaurant;
        this.review = review;
        this.rating = rating;
        this.postDate = postDate;
    }

    public User getUser() {
        return this.user;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {this.review = review;}

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public LocalDateTime getDate() {
        return this.postDate;
    }

}
