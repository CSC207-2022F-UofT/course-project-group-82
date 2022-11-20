package com.ouieat.responses;

import com.ouieat.models.Restaurant;
import com.ouieat.responses.models.FilteredRestaurantsResponseData;
import java.util.ArrayList;

public class FilterResponse {

    public static Response getFilteredRestaurantsResponse(
        ArrayList<Restaurant> restaurantArrayList
    ) {
        return new Response(
            "success",
            new FilteredRestaurantsResponseData(restaurantArrayList),
            "filter-controller",
            "client"
        );
    }
}
