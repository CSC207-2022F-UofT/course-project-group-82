package com.ouieat.responses.models;

import com.ouieat.models.Recommendation;
import com.ouieat.responses.ResponseData;
import java.util.ArrayList;

public class RecommendationResponseData extends ResponseData {

    public final ArrayList<Recommendation> allRecommendations;

    public RecommendationResponseData(
        ArrayList<Recommendation> allRecommendations
    ) {
        this.allRecommendations = allRecommendations;
    }
}
