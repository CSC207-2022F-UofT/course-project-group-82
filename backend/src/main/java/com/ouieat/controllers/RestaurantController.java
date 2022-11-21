package com.ouieat.controllers;

import com.ouieat.repository.RestaurantRepository;
import com.ouieat.requests.RestaurantRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    public final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping(path = "/getRestaurantsByName", produces = "application/json")
    public String getRestaurantsByName(@RequestParam String name) {
        return RestaurantRequests.getRestaurantsByName(
            restaurantRepository,
            name
        );
    }
}
