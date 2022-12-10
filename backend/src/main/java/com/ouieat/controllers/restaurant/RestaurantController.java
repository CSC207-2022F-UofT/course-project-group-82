package com.ouieat.controllers.restaurant;

import com.ouieat.controllers.handler.Controller;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.restaurant.AuthenticatedRestaurantRequests;
import com.ouieat.requests.restaurant.UnauthenticatedRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController
    extends Controller<RestaurantInteractor, AuthenticatedRestaurantRequests, UnauthenticatedRestaurantRequest> {

    @Autowired
    public RestaurantController(
        UserInteractor userInteractor,
        RestaurantInteractor interactor,
        UnauthenticatedRestaurantRequest unauthenticatedRestaurantRequest,
        AuthenticatedRestaurantRequests authenticatedRestaurantRequests
    ) {
        super(
            userInteractor,
            interactor,
            authenticatedRestaurantRequests,
            unauthenticatedRestaurantRequest
        );
    }

    @GetMapping(path = "/getRestaurantsByName", produces = "application/json")
    public String getRestaurantsByName(@RequestParam String name) {
        return unauthenticatedRequest
            .handle(
                interactor,
                name,
                unauthenticatedRequest.getRestaurantsByName
            )
            .getJsonString();
    }

    @GetMapping(path = "/getRestaurantsById", produces = "application/json")
    public String getRestaurantsById(@RequestParam String id) {
        return unauthenticatedRequest
            .handle(interactor, id, unauthenticatedRequest.getRestaurantById)
            .getJsonString();
    }
}
