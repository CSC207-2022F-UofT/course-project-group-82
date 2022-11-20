package com.ouieat.requests;

import com.ouieat.models.RestaurantRecommendation;
import com.ouieat.repository.RecommendationPostingRepository;
import com.ouieat.implementation.RecommendationPostingImplementation;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;

public class RecommendationPostingRequests{
    public static String postRestaurantRecommendation(
            RecommendationPostingRepository recommendationPostingRepository,
            RestaurantRecommendation newRestaurantRecommendation) {

        // Validate that newRestaurantRecommendation has all required properties, and they are not empty
        if (newRestaurantRecommendation == null) return ExceptionResponses
                .MissingRequestParametersResponse()
                .getJsonString();

        // Ensure no values are empty when being sent
        if (newRestaurantRecommendation.getRestaurant() == null ||
                newRestaurantRecommendation.getReview() == "" ||
                newRestaurantRecommendation.getRating() < 0 ||
                newRestaurantRecommendation.getRating() > 10
        ) return ExceptionResponses
                .MissingRequestParametersResponse()
                .getJsonString();

        Response response = RecommendationPostingImplementation.saveNewRestaurantRecommendation(
                recommendationPostingRepository,
                newRestaurantRecommendation
        );
        return response.getJsonString();

    }
}
