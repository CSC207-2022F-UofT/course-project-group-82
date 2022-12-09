package com.ouieat.requests.restaurant;

import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.handler.AuthenticatedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedRestaurantRequests
    extends AuthenticatedRequest<RestaurantInteractor> {

    @Autowired
    public AuthenticatedRestaurantRequests(UserInteractor userInteractor){
        super(userInteractor);
    }
}
