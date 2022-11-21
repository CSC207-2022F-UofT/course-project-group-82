package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.Restaurant;
import com.ouieat.repository.RestaurantRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.RestaurantResponses;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.logging.log4j.Level;

public class RestaurantImplementation {

    public static Response getRestaurantsByNameImplementation(
        RestaurantRepository restaurantRepository,
        String name
    ) {
        try {
            ArrayList<Restaurant> restaurants = new ArrayList<>(
                restaurantRepository.findAll()
            );

            ArrayList<Restaurant> returnedRestaurants = new ArrayList<>();
            for (Restaurant r : restaurants) {
                if (r.getName().toLowerCase().contains(name.toLowerCase())) {
                    returnedRestaurants.add(r);
                }
            }
            OuiLogger.log(
                Level.INFO,
                "Found " +
                returnedRestaurants.size() +
                " restaurants with name: " +
                name
            );
            return RestaurantResponses.RestaurantByNameResponse(
                returnedRestaurants
            );
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Error in getRestaurantsByNameImplementation: " + e.getMessage()
            );
            return ExceptionResponses.MissingRequestParametersResponse();
        }
    }

    public static Response getRestaurantsByIdImplementation(
        RestaurantRepository restaurantRepository,
        String id
    ) {
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if (restaurant.isPresent()) {
                OuiLogger.log(Level.INFO, "Found restaurant with id: " + id);
                return RestaurantResponses.RestaurantByIdResponse(
                    restaurant.get()
                );
            } else {
                OuiLogger.log(Level.INFO, "No restaurant found with id: " + id);
                return RestaurantResponses.RestaurantByIdResponse(null);
            }
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Error in getRestaurantsByIdImplementation: " + e.getMessage()
            );
            return ExceptionResponses.MissingRequestParametersResponse();
        }
    }
}
