package com.ouieat.controllers.recommendation;

import com.ouieat.controllers.Controller;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
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
    extends Controller<RecommendationInteractor> {

    private final UnauthenticatedRecommendationRequest unauthenticatedRecommendationRequest;
    private final AuthenticatedRecommendationRequest authenticatedRecommendationRequest;

    @Autowired
    public RecommendationController(
        RecommendationInteractor interactor,
        UnauthenticatedRecommendationRequest unauthenticatedRecommendationRequest,
        AuthenticatedRecommendationRequest authenticatedRecommendationRequest
    ) {
        super(interactor);
        this.unauthenticatedRecommendationRequest =
            unauthenticatedRecommendationRequest;
        this.authenticatedRecommendationRequest =
            authenticatedRecommendationRequest;
    }

    @PostMapping(
        value = "/postRestaurantRecommendation",
        consumes = "application/json",
        produces = "application/json"
    )
    public String postRestaurantRecommendation(
        @RequestBody Recommendation newRecommendation
    ) {
        return authenticatedRecommendationRequest
            .handle(
                interactor,
                newRecommendation.getUserId(),
                newRecommendation,
                authenticatedRecommendationRequest.postRecommendation
            )
            .getJsonString();
    }

    @GetMapping(
        value = "/getRestaurantRecommendationsForUser",
        produces = "application/json"
    )
    public String getRestaurantRecommendationsForUser(String userId) {
        return authenticatedRecommendationRequest
            .handle(
                interactor,
                userId,
                authenticatedRecommendationRequest.getRecommendationsForUser
            )
            .getJsonString();
    }

    public UnauthenticatedRecommendationRequest getUnauthenticatedRecommendationRequest() {
        return unauthenticatedRecommendationRequest;
    }

    public AuthenticatedRecommendationRequest getAuthenticatedRecommendationRequest() {
        return authenticatedRecommendationRequest;
    }
}
