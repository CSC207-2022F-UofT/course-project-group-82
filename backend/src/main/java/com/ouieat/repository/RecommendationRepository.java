package com.ouieat.repository;

import com.ouieat.models.recommendation.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommendationRepository
    extends MongoRepository<Recommendation, String> {}
