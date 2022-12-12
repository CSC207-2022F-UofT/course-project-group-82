package com.ouieat.requests.restaurant;

import com.ouieat.implementation.restaurant.RestaurantImplementation;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedRestaurantRequest
    extends UnauthenticatedRequest<RestaurantInteractor, RestaurantImplementation> {

    public final Function<String, Response> getRestaurantsByName = (String name) -> {
        if (name == null || name.isBlank() || name.isEmpty()) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        return implementation.getRestaurantsByName(name);
    };

    public final Function<String, Response> getRestaurantById = (String id) -> {
        if (id == null || id.isBlank() || id.isEmpty()) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        if (interactor.findById(id) == null) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }

        return implementation.getRestaurantById(id);
    };

    @Autowired
    public UnauthenticatedRestaurantRequest(
        UserInteractor userInteractor,
        RestaurantInteractor interactor,
        RestaurantImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
