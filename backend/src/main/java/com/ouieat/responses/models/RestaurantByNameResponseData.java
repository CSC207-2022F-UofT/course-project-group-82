package com.ouieat.responses.models;

import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class RestaurantByNameResponseData extends ResponseData {

    public ArrayList<Restaurant> restaurants;

    public RestaurantByNameResponseData(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
