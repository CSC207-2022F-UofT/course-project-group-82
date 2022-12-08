package com.ouieat.interactor.recommendation;

import com.ouieat.interactor.Interactor;
import com.ouieat.models.recommendation.Recommendation;
import com.ouieat.repository.RecommendationRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendationInteractor
    extends Interactor<Recommendation, RecommendationRepository> {

    @Autowired
    public RecommendationInteractor(RecommendationRepository repository) {
        super(repository);
    }

    public ArrayList<Recommendation> getRecommendationsForUser(String userId) {
        ArrayList<Recommendation> recommendations = new ArrayList<>(
            repository.findAll()
        );
        ArrayList<Recommendation> result = new ArrayList<>();
        for (Recommendation recommendation : recommendations) {
            if (recommendation.getUserId().equals(userId)) {
                result.add(recommendation);
            }
        }
        return result;
    }
}
