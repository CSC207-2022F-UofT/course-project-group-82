package com.ouieat.implementation.recommendation.methods;

import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.recommendation.RecommendationResponses;
import java.util.ArrayList;

public interface GetRecommendationsForUser {
    static Response apply(
        RecommendationInteractor interactor,
        User loggedInUser
    ) {
        try {
            ArrayList<Recommendation> recommendations = interactor.getRecommendationsForUser(
                loggedInUser.getId()
            );
            for (String friendId : loggedInUser.getFriendIds()) {
                recommendations.addAll(
                    interactor.getRecommendationsForUser(friendId)
                );
            }
            return RecommendationResponses.GetRecommendationsForUserResponse(
                recommendations
            );
        } catch (Exception e) {
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
