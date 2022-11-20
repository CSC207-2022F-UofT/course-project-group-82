package com.ouieat.implementation;

import com.ouieat.models.Filter;
import com.ouieat.models.Restaurant;
import com.ouieat.repository.RestaurantRepository;
import com.ouieat.responses.FilterResponse;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class FilterImplementation {

    /*
     * Take in a FilterRepository object filterRepository.
     * filters through the database of all the restaurants
     * to find the restaurant that matches the input filterRepository
     *  objects
     */
    public static String filterImplementationFunc(
        RestaurantRepository restaurantRepository,
        Filter filter
    ) {
        String[] cuisine = filter.getCuisine();
        ArrayList<Restaurant> filteredRestaurants = new ArrayList<Restaurant>();
        for (String c : cuisine) {
            filteredRestaurants.addAll(
                restaurantRepository.findRestaurantByCuisine(c)
            );
        }

        return FilterResponse
            .getFilteredRestaurantsResponse(filteredRestaurants)
            .getJsonString();
    }
}
