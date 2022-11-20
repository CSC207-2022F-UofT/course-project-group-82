package com.ouieat.controllers;

import com.ouieat.models.Restaurant;
import com.ouieat.models.RestaurantRecommendation;
import com.ouieat.repository.RecommendationPostingRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.RecommendationPostingRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendationPostingController {

    public final RecommendationPostingRepository recommendationPostingRepository;

    @Autowired
    public RecommendationPostingController(
        RecommendationPostingRepository recommendationPostingRepository
    ) {
        this.recommendationPostingRepository = recommendationPostingRepository;
    }

    @PostMapping(
        path = "/postRestaurantRecommendation",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String postRestaurantRecommendation(
        @RequestBody RestaurantRecommendation newRestaurantRecommendation
    ) {
        return RecommendationPostingRequests.postRestaurantRecommendation(
            recommendationPostingRepository,
            newRestaurantRecommendation
        );
    }
}
