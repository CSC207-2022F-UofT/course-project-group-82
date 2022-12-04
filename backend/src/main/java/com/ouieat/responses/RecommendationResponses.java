package com.ouieat.responses;

import com.ouieat.models.Recommendation;
import com.ouieat.responses.models.NullResponseData;
import com.ouieat.responses.models.RecommendationResponseData;
import java.util.ArrayList;

public class RecommendationResponses {

    public static Response SavePostResponse(String status, String name) {
        return new Response(
            status,
            new NullResponseData(),
            "saveNewRestaurantRecommendation",
            "client-post"
        );
    }

    public static Response GetAllRecommendationsResponse(
        ArrayList<Recommendation> allRecommendations
    ) {
        return new Response(
            "success",
            new RecommendationResponseData(allRecommendations),
            "getAllRestaurantRecommendations",
            "client-get"
        );
    }
}
