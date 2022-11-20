package com.ouieat.requests;

import com.ouieat.implementation.FilterImplementation;
import com.ouieat.models.Filter;
import com.ouieat.repository.RestaurantRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import java.util.Objects;

public class FilterRequests {

    public static String filterRestaurantsRequest(
        RestaurantRepository restaurantRepository,
        Filter filter
    ) {
        if (
            filter.getDistance() != 0.0 ||
            filter.getUserLatitude() != 0.0 ||
            filter.getUserLongitude() != 0.0 ||
            Objects.isNull(filter.getCuisine()) ||
            filter.getPriceRangeMin() != 0 ||
            filter.getPriceRangeMax() != 0
        ) {
            // do stuff
            return FilterImplementation.filterImplementationFunc(
                restaurantRepository,
                filter
            );
        } else {
            return ExceptionResponses
                .MissingRequestParametersResponse()
                .getJsonString();
        }
    }
}
