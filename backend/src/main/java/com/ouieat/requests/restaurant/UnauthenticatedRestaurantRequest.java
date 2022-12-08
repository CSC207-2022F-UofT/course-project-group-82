package com.ouieat.requests.restaurant;

import com.ouieat.implementation.restaurant.RestaurantImplementation;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedRestaurantRequest
    extends UnauthenticatedRequest<RestaurantInteractor> {

    public FunctionalInterfaces.Function2<RestaurantInteractor, String, Response> getRestaurantsByName = (
        RestaurantInteractor interactor,
        String name
    ) -> {
        if (name == null || name.isBlank() || name.isEmpty()) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        return RestaurantImplementation.getRestaurantsByName(interactor, name);
    };

    public FunctionalInterfaces.Function2<RestaurantInteractor, String, Response> getRestaurantById = (
        RestaurantInteractor interactor,
        String id
    ) -> {
        if (id == null || id.isBlank() || id.isEmpty()) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        if (interactor.findById(id) == null) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        return RestaurantImplementation.getRestaurantById(interactor, id);
    };
}
