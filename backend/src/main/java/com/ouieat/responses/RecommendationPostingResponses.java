package com.ouieat.responses;

public class RecommendationPostingResponses {
    public static Response SavePostResponse(
            String status,
            String name
    ) {
        return new Response(status, name, "saveNewRestaurantRecommendation", "client-post");
    }
}
