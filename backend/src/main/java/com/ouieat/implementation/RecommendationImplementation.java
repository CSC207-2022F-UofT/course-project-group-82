package com.ouieat.implementation;

import com.ouieat.OuiLogger;
import com.ouieat.models.Recommendation;
import com.ouieat.repository.RecommendationRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.RecommendationResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.UserResponses;
import java.util.ArrayList;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

@Service
public class RecommendationImplementation {

    public static Response saveNewRestaurantRecommendation(
        RecommendationRepository recommendationRepository,
        Recommendation newRecommendation
    ) {
        /*
         * Saving a restaurant recommendation
         * Return a failure response if unable to save
         */

        try {
            recommendationRepository.save(newRecommendation);
            OuiLogger.log(
                Level.INFO,
                "Successfully saved the new recommendation for: " +
                newRecommendation.getRestaurantId()
            );
            return UserResponses.RegistrationResponse(
                "success",
                newRecommendation.getRestaurantId()
            );
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Failed to save the new recommendation for: " +
                newRecommendation.getRestaurantId()
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return RecommendationResponses.SavePostResponse(
                "failure",
                "Error while saving the recommendation"
            );
        }
    }

    public static Response getAllRestaurantRecommendations(
        RecommendationRepository recommendationRepository
    ) {
        /*
         * Get all restaurant recommendations
         * Return a failure response if unable to get
         */

        try {
            ArrayList<Recommendation> allRecommendations = new ArrayList<>(
                recommendationRepository.findAll()
            );
            OuiLogger.log(
                Level.INFO,
                "Successfully retrieved all recommendations"
            );
            return RecommendationResponses.GetAllRecommendationsResponse(
                allRecommendations
            );
        } catch (Exception e) {
            OuiLogger.log(
                Level.ERROR,
                "Failed to get all restaurant recommendations"
            );
            OuiLogger.log(Level.ERROR, e.getMessage());
            return ExceptionResponses.ServerExceptionResponse();
        }
    }
}
