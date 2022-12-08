package com.ouieat.requests.restaurant;

import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.requests.handler.AuthenticatedRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedRestaurantRequests
    extends AuthenticatedRequest<RestaurantInteractor> {}
