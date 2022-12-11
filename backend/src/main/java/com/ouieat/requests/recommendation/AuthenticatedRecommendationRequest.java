package com.ouieat.requests.recommendation;

import com.ouieat.implementation.recommendation.RecommendationImplementation;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.models.user.User;
import com.ouieat.requests.handler.AuthenticatedRequest;
import com.ouieat.requests.handler.FunctionalInterfaces;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedRecommendationRequest
    extends AuthenticatedRequest<RecommendationInteractor, RecommendationImplementation> {

    public FunctionalInterfaces.Function2<User, Recommendation, Response> postRecommendation = (
        User loggedInUser,
        Recommendation recommendation
    ) -> {
        if (
            recommendation == null || recommendation.getRestaurantId() == null
        ) {
            return ExceptionResponses.MissingRequestParametersResponse();
        }
        return implementation.postRecommendation(loggedInUser, recommendation);
    };

    public Function<User, Response> getRecommendationsForUser = loggedInUser ->
        implementation.getRecommendationsForUser(loggedInUser);

    @Autowired
    public AuthenticatedRecommendationRequest(
        UserInteractor userInteractor,
        RecommendationInteractor interactor,
        RecommendationImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
