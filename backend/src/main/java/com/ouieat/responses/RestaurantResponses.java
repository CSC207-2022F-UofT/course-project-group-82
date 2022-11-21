package com.ouieat.responses;

import com.ouieat.models.Restaurant;
import com.ouieat.responses.models.RestaurantByIdResponseData;
import com.ouieat.responses.models.RestaurantByNameResponseData;
import java.util.ArrayList;

public class RestaurantResponses {

    public static Response RestaurantByNameResponse(
        ArrayList<Restaurant> restaurants
    ) {
        return new Response(
            "success",
            new RestaurantByNameResponseData(restaurants),
            "getRestaurantsByName",
            "client"
        );
    }

    public static Response RestaurantByIdResponse(Restaurant restaurant) {
        return new Response(
            "success",
            new RestaurantByIdResponseData(restaurant),
            "getRestaurantById",
            "client"
        );
    }
}
