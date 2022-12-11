package com.ouieat.requests.recommendation;

import com.ouieat.implementation.recommendation.RecommendationImplementation;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedRecommendationRequest
    extends UnauthenticatedRequest<RecommendationInteractor, RecommendationImplementation> {

    @Autowired
    public UnauthenticatedRecommendationRequest(
        UserInteractor userInteractor,
        RecommendationInteractor interactor,
        RecommendationImplementation implementation
    ) {
        super(userInteractor, interactor, implementation);
    }
}
