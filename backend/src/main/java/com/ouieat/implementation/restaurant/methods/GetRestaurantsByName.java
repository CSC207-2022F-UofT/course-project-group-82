package com.ouieat.implementation.restaurant.methods;

import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.restaurant.RestaurantResponses;

public interface GetRestaurantsByName {
    static Response apply(RestaurantInteractor interactor, String name) {
        try {
            return RestaurantResponses.RestaurantsByNameResponse(
                interactor.findRestaurantByName(name)
            );
        } catch (Exception e) {
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
