package com.ouieat.responses.restaurant;

import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.handler.ResponseData;
import java.util.ArrayList;

public class RestaurantResponses {

    public static Response RestaurantsByNameResponse(
        ArrayList<Restaurant> restaurants
    ) {
        return new Response("success", new ResponseData<>(restaurants));
    }

    public static Response RestaurantByIdResponse(Restaurant restaurant) {
        return new Response("success", new ResponseData<>(restaurant));
    }
}
