package com.ouieat.recommendation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ouieat.ServiceTest;
import com.ouieat.implementation.recommendation.RecommendationImplementation;
import com.ouieat.interactor.recommendation.RecommendationInteractor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.requests.recommendation.AuthenticatedRecommendationRequest;
import com.ouieat.requests.recommendation.UnauthenticatedRecommendationRequest;
import com.ouieat.responses.handler.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RecommendationServiceTest
    extends ServiceTest<RecommendationInteractor, AuthenticatedRecommendationRequest, UnauthenticatedRecommendationRequest> {

    public RecommendationServiceTest() {
        super(
            RecommendationInteractor.class,
            AuthenticatedRecommendationRequest.class,
            UnauthenticatedRecommendationRequest.class,
            RecommendationImplementation.class
        );
    }

    //  Route: /postRecommendation

    @Test
    public void postRestaurantRecommendationSuccess() {
        when(interactor.save(any(Recommendation.class)))
            .thenAnswer(i -> i.getArguments()[0]);
        Recommendation recommendation = new Recommendation(
            getTestUserDefault().getId(),
            "Dec 09 2022",
            "Mc Donald's",
            new String[] { "" },
            false
        );
        Response response = authenticatedRequest.handle(
            getTestUserDefault().getId(),
            recommendation,
            authenticatedRequest.postRecommendation
        );
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).isEqualTo(recommendation);
    }

    @Test
    public void postRestaurantRecommendationFail() {
        Response response = authenticatedRequest.handle(
            getTestUserDefault().getId(),
            null,
            authenticatedRequest.postRecommendation
        );
        assertThat(response.status).isEqualTo("error");
    }

    // Route: /getRestaurantRecommendationsForUser

    @Test
    public void getRestaurantRecommendationsForUserSuccess() {
        Recommendation recommendation = new Recommendation(
            getTestUserDefault().getId(),
            "Dec 09 2022",
            "Mc Donald's",
            new String[] { "" },
            false
        );
        when(interactor.getRecommendationsForUser(getTestUserDefault().getId()))
            .thenReturn(new ArrayList<>(List.of(recommendation)));
        Response response = authenticatedRequest.handle(
            getTestUserDefault().getId(),
            authenticatedRequest.getRecommendationsForUser
        );
        assertThat(response.responseData.data).isNotNull();
        assertThat(response.status).isEqualTo("success");
    }

    @Test
    public void getRestaurantRecommendationsForUserFail() {
        when(interactor.getRecommendationsForUser(getTestUserDefault().getId()))
            .thenReturn(new ArrayList<>());
        Response response = authenticatedRequest.handle(
            "INVALID_ID",
            authenticatedRequest.getRecommendationsForUser
        );
        assertThat(response.responseData.data)
            .isEqualTo("Invalid user credentials");
        assertThat(response.status).isEqualTo("error");
    }
}
