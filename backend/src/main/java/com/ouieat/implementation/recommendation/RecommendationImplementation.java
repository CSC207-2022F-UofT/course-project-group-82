package com.ouieat.implementation.recommendation;

import com.ouieat.implementation.recommendation.methods.GetRecommendationsForUser;
import com.ouieat.implementation.recommendation.methods.PostRecommendation;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.models.user.User;
import com.ouieat.responses.handler.Response;

public class RecommendationImplementation {

    public static Response postRecommendation(
        RecommendationInteractor interactor,
        User loggedInUser,
        Recommendation recommendation
    ) {
        return PostRecommendation.apply(
            interactor,
            loggedInUser,
            recommendation
        );
    }

    public static Response getRecommendationsForUser(
        RecommendationInteractor interactor,
        User loggedInUser
    ) {
        return GetRecommendationsForUser.apply(interactor, loggedInUser);
    }
}
