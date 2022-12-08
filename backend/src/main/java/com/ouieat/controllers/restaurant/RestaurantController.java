package com.ouieat.controllers.restaurant;

import com.ouieat.controllers.Controller;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.requests.restaurant.AuthenticatedRestaurantRequests;
import com.ouieat.requests.restaurant.UnauthenticatedRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController extends Controller<RestaurantInteractor> {

    private final UnauthenticatedRestaurantRequest unauthenticatedRestaurantRequest;

    private final AuthenticatedRestaurantRequests authenticatedRestaurantRequests;

    @Autowired
    public RestaurantController(
        RestaurantInteractor interactor,
        UnauthenticatedRestaurantRequest unauthenticatedRestaurantRequest,
        AuthenticatedRestaurantRequests authenticatedRestaurantRequests
    ) {
        super(interactor);
        this.unauthenticatedRestaurantRequest =
            unauthenticatedRestaurantRequest;
        this.authenticatedRestaurantRequests = authenticatedRestaurantRequests;
    }

    @GetMapping(path = "/getRestaurantsByName", produces = "application/json")
    public String getRestaurantsByName(@RequestParam String name) {
        return unauthenticatedRestaurantRequest
            .handle(
                interactor,
                name,
                unauthenticatedRestaurantRequest.getRestaurantsByName
            )
            .getJsonString();
    }

    @GetMapping(path = "/getRestaurantsById", produces = "application/json")
    public String getRestaurantsById(@RequestParam String id) {
        return unauthenticatedRestaurantRequest
            .handle(
                interactor,
                id,
                unauthenticatedRestaurantRequest.getRestaurantById
            )
            .getJsonString();
    }

    public UnauthenticatedRestaurantRequest getUnauthenticatedRestaurantRequest() {
        return unauthenticatedRestaurantRequest;
    }

    public AuthenticatedRestaurantRequests getAuthenticatedRestaurantRequests() {
        return authenticatedRestaurantRequests;
    }
}
