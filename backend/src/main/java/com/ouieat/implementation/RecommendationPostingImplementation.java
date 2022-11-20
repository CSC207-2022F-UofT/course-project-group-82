package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.RestaurantRecommendation;
import com.ouieat.repository.RecommendationPostingRepository;
import com.ouieat.responses.RecommendationPostingResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.UserResponses;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

@Service
public class RecommendationPostingImplementation {
    public static Response saveNewRestaurantRecommendation(
            RecommendationPostingRepository recommendationPostingRepository,
            RestaurantRecommendation newRestaurantRecommendation
    ) {
        /*
         * Saving a restaurant recommendation
         * Return a failure response if unable to save
         */

        try {
            recommendationPostingRepository.save(newRestaurantRecommendation);
            OuiLogger.log(
                    Level.INFO,
                    "Successfully saved the new recommendation for: " + newRestaurantRecommendation.getRestaurant().getName()
            );
            return UserResponses.RegistrationResponse(
                    "success",
                    newRestaurantRecommendation.getRestaurant().getName()
            );
        } catch (Exception e) {
            OuiLogger.log(
                    Level.ERROR,

                    "Failed to save the new recommendation for: " + newRestaurantRecommendation.getRestaurant().getName()
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return RecommendationPostingResponses.SavePostResponse(
                    "failure",
                    "Error while saving the recommendation"
            );
        }
    }

}
