package com.ouieat.repository;

import com.ouieat.models.restaurant.Restaurant;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RestaurantRepository
    extends MongoRepository<Restaurant, String> {}
