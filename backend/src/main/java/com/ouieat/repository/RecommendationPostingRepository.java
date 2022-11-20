package com.ouieat.repository;

import com.ouieat.models.Restaurant;
import com.ouieat.models.RestaurantRecommendation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RecommendationPostingRepository
    extends MongoRepository<RestaurantRecommendation, String> {
    @Query("{username:  '?0', postDate:  '?1'}")
    ArrayList<RestaurantRecommendation> findRestaurantRecommendationByUserAndPostDate(
        String username,
        LocalDateTime postDate
    );
}
