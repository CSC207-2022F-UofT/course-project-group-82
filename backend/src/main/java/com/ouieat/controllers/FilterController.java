package com.ouieat.controllers;

import com.ouieat.models.Filter;
import com.ouieat.repository.RestaurantRepository;
import com.ouieat.requests.FilterRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    public final RestaurantRepository restaurantRepository;

    @Autowired
    public FilterController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping(
        path = "/filterRestaurant",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String filterRestaurants(@RequestBody Filter filterConditions) {
        return FilterRequests.filterRestaurantsRequest(
            restaurantRepository,
            filterConditions
        );
    }
}
