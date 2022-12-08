package com.ouieat.responses.recommendation;

import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.handler.ResponseData;
import java.util.ArrayList;

public class RecommendationResponses {

    public static Response CreatedRecommendationResponse(
        Recommendation recommendation
    ) {
        return new Response("success", new ResponseData<>(recommendation));
    }

    public static Response GetRecommendationsForUserResponse(
        ArrayList<Recommendation> recommendations
    ) {
        return new Response("success", new ResponseData<>(recommendations));
    }
}
