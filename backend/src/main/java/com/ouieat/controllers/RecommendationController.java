package com.ouieat.controllers;

import com.ouieat.models.Recommendation;
import com.ouieat.models.UserCredentials;
import com.ouieat.repository.RecommendationRepository;
import com.ouieat.repository.UserRepository;
import com.ouieat.requests.RecommendationRequests;
import com.ouieat.requests.UserRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendationController {

    public final RecommendationRepository recommendationRepository;
    public final UserRepository userRepository;

    @Autowired
    public RecommendationController(
        RecommendationRepository recommendationRepository,
        UserRepository userRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(
        path = "/postRestaurantRecommendation",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String postRestaurantRecommendation(
        @RequestBody Recommendation newRecommendation
    ) {
        return RecommendationRequests.postRestaurantRecommendation(
            recommendationRepository,
            newRecommendation
        );
    }

    // For now, we will set up a route to get all recommendations
    // Later we will set up a route to get recommendations for a specific user
    @GetMapping(
        path = "/getAllRestaurantRecommendations",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAllRestaurantRecommendations() {
        return RecommendationRequests.getAllRestaurantRecommendations(
            recommendationRepository
        );
    }

    @GetMapping(
        path = "/getRestaurantRecommendationsForUser",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getRestaurantRecommendationsForUserFriends(
        @RequestParam String userId
    ) {
        return UserRequests.getFriendRecommendations(
            userRepository,
            recommendationRepository,
            UserCredentials.fromUserID(userId)
        );
    }
}
