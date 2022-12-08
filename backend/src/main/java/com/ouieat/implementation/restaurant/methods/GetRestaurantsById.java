package com.ouieat.implementation.restaurant.methods;

import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.restaurant.RestaurantResponses;

public interface GetRestaurantsById {
    static Response apply(
        RestaurantInteractor interactor,
        String restaurantId
    ) {
        Restaurant restaurant = interactor.findById(restaurantId);
        if (restaurant == null) {
            return ExceptionResponses.ServerExceptionResponse();
        }
        return RestaurantResponses.RestaurantByIdResponse(restaurant);
    }
}
