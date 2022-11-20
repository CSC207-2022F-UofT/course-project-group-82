package com.ouieat.responses;

import com.ouieat.responses.models.NullResponseData;

public class RecommendationPostingResponses {

    public static Response SavePostResponse(String status, String name) {
        return new Response(
            status,
            new NullResponseData(),
            "saveNewRestaurantRecommendation",
            "client-post"
        );
    }
}
