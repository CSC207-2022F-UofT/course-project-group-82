package com.ouieat.implementation.recommendation.methods;

import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.recommendation.RecommendationResponses;

public interface PostRecommendation {
    static Response apply(
        RecommendationInteractor interactor,
        User loggedInUser,
        Recommendation recommendation
    ) {
        try {
            interactor.save(recommendation);
            return RecommendationResponses.CreatedRecommendationResponse(
                recommendation
            );
        } catch (Exception e) {
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
