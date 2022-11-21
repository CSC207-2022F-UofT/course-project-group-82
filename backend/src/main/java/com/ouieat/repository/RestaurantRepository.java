package com.ouieat.repository;

import com.ouieat.models.Restaurant;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RestaurantRepository
    extends MongoRepository<Restaurant, String> {
    @Query("{distance:  '?0'}")
    ArrayList<Restaurant> findRestaurantByDistance(String distance);

    @Query("{cuisine:  '?0'}")
    ArrayList<Restaurant> findRestaurantByCuisine(String cuisine);

    @Query("{priceRangeMin:  '?0'}")
    ArrayList<Restaurant> findRestaurantByPriceRangeMin(int priceRangeMin);

    @Query("{priceRangeMax:  '?0'}")
    ArrayList<Restaurant> findRestaurantByPriceRangeMax(int PriceRangeMax);
}
