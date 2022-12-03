package com.ouieat.requests;

import com.ouieat.implementation.RecommendationImplementation;
import com.ouieat.models.Recommendation;
import com.ouieat.repository.RecommendationRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import java.util.Objects;

public class RecommendationRequests {

    public static String postRestaurantRecommendation(
        RecommendationRepository recommendationRepository,
        Recommendation newRecommendation
    ) {
        // Validate that newRestaurantRecommendation has all required properties, and they are not empty
        if (newRecommendation == null) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        // Ensure no values are empty when being sent
        if (
            newRecommendation.getRestaurantId() == null
        ) return ExceptionResponses
            .MissingRequestParametersResponse()
            .getJsonString();

        Response response = RecommendationImplementation.saveNewRestaurantRecommendation(
            recommendationRepository,
            newRecommendation
        );
        return response.getJsonString();
    }

    public static String getAllRestaurantRecommendations(
        RecommendationRepository recommendationRepository
    ) {
        // Since this get request doesn't have any parameters, we can just call the implementation directly
        return RecommendationImplementation
            .getAllRestaurantRecommendations(recommendationRepository)
            .getJsonString();
    }
}
