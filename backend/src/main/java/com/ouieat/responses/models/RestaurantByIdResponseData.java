package com.ouieat.responses.models;

import com.ouieat.models.Restaurant;
import com.ouieat.responses.ResponseData;

public class RestaurantByIdResponseData extends ResponseData {

    public final Restaurant restaurant;

    public RestaurantByIdResponseData(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
