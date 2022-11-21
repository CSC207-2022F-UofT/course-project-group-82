package com.ouieat.requests;

import com.ouieat.implementation.RestaurantImplementation;
import com.ouieat.repository.RestaurantRepository;
import com.ouieat.responses.ExceptionResponses;

public class RestaurantRequests {

    public static String getRestaurantsByName(
        RestaurantRepository restaurantRepository,
        String name
    ) {
        if (name != null) {
            return RestaurantImplementation
                .getRestaurantsByNameImplementation(restaurantRepository, name)
                .getJsonString();
        } else {
            return ExceptionResponses
                .MissingRequestParametersResponse()
                .getJsonString();
        }
    }
}
