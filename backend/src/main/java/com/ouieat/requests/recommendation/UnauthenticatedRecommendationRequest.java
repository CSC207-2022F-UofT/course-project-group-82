package com.ouieat.requests.recommendation;

import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.requests.handler.UnauthenticatedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnauthenticatedRecommendationRequest
    extends UnauthenticatedRequest<RecommendationInteractor> {

    @Autowired
    public UnauthenticatedRecommendationRequest(
        RecommendationInteractor interactor
    ) {
        super(interactor);
    }
}
