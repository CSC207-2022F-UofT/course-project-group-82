package com.ouieat.responses;

import com.ouieat.models.Restaurant;
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
}
