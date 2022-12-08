package com.ouieat.implementation.restaurant;

import com.ouieat.implementation.restaurant.methods.GetRestaurantsById;
import com.ouieat.implementation.restaurant.methods.GetRestaurantsByName;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.responses.handler.Response;

public class RestaurantImplementation {

    // Find restaurants by name
    public static Response getRestaurantsByName(
        RestaurantInteractor interactor,
        String name
    ) {
        return GetRestaurantsByName.apply(interactor, name);
    }

    // Find restaurant by id
    public static Response getRestaurantById(
        RestaurantInteractor interactor,
        String id
    ) {
        return GetRestaurantsById.apply(interactor, id);
    }
}
