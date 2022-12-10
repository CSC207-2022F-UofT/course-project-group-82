package com.ouieat.implementation.recommendation;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.implementation.recommendation.methods.GetRecommendationsForUser;
import com.ouieat.implementation.recommendation.methods.PostRecommendation;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.models.user.User;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class RecommendationImplementation
    extends Implementation<RecommendationInteractor> {

    public RecommendationImplementation(
        UserInteractor userInteractor,
        RecommendationInteractor interactor
    ) {
        super(userInteractor, interactor);
    }

    public Response postRecommendation(
        User loggedInUser,
        Recommendation recommendation
    ) {
        return PostRecommendation.apply(
            this.interactor,
            loggedInUser,
            recommendation
        );
    }

    public Response getRecommendationsForUser(User loggedInUser) {
        return GetRecommendationsForUser.apply(this.interactor, loggedInUser);
    }
}
