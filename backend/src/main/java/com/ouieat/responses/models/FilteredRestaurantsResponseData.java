package com.ouieat.responses.models;

import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class FilteredRestaurantsResponseData extends ResponseData {

    public ArrayList<Restaurant> restaurants;

    public FilteredRestaurantsResponseData(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
