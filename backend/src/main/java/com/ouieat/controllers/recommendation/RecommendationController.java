package com.ouieat.controllers.recommendation;

import com.ouieat.controllers.handler.Controller;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.requests.recommendation.AuthenticatedRecommendationRequest;
import com.ouieat.requests.recommendation.UnauthenticatedRecommendationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController
    extends Controller<RecommendationInteractor, AuthenticatedRecommendationRequest, UnauthenticatedRecommendationRequest> {

    @Autowired
    public RecommendationController(
        UserInteractor userInteractor,
        RecommendationInteractor interactor,
        UnauthenticatedRecommendationRequest unauthenticatedRecommendationRequest,
        AuthenticatedRecommendationRequest authenticatedRecommendationRequest
    ) {
        super(
            userInteractor,
            interactor,
            authenticatedRecommendationRequest,
            unauthenticatedRecommendationRequest
        );
    }

    @PostMapping(
        value = "/postRestaurantRecommendation",
        consumes = "application/json",
        produces = "application/json"
    )
    public String postRestaurantRecommendation(
        @RequestBody Recommendation newRecommendation
    ) {
        return authenticatedRequest
            .handle(
                interactor,
                newRecommendation.getUserId(),
                newRecommendation,
                authenticatedRequest.postRecommendation
            )
            .getJsonString();
    }

    @GetMapping(
        value = "/getRestaurantRecommendationsForUser",
        produces = "application/json"
    )
    public String getRestaurantRecommendationsForUser(String userId) {
        return authenticatedRequest
            .handle(
                interactor,
                userId,
                authenticatedRequest.getRecommendationsForUser
            )
            .getJsonString();
    }
}
