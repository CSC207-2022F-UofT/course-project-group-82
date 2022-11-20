package com.ouieat.repository;
import com.ouieat.models.RestaurantRecommendation;
import com.ouieat.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface RecommendationPostingRepository extends MongoRepository<RestaurantRecommendation, String>{

    @Query("{username:  '?0', postDate:  '?1'}")
    ArrayList<RestaurantRecommendation> findRestaurantRecommendationByUserAndPostDate(
            String username,
            LocalDateTime postDate
    );

}

