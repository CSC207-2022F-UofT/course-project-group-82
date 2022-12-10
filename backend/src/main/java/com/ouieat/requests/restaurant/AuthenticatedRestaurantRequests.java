package com.ouieat.requests.restaurant;

import com.ouieat.implementation.restaurant.RestaurantImplementation;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.handler.AuthenticatedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedRestaurantRequests
    extends AuthenticatedRequest<RestaurantInteractor, RestaurantImplementation> {

    @Autowired
    public AuthenticatedRestaurantRequests(
        UserInteractor userInteractor,
        RestaurantInteractor interactor,
        RestaurantImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
