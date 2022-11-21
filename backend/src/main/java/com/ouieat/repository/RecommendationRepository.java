package com.ouieat.repository;

import com.ouieat.models.Recommendation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RecommendationRepository
    extends MongoRepository<Recommendation, String> {
    @Query("{username:  '?0', postDate:  '?1'}")
    ArrayList<Recommendation> findRestaurantRecommendationByUserAndPostDate(
        String username,
        LocalDateTime postDate
    );
}
