package com.ouieat.repository;

import com.ouieat.models.Filter;
import com.ouieat.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;

public abstract class FilterRepository implements MongoRepository<User, String> {
    @Query("{distance:  '?0'}")
    ArrayList<Filter> findRestaurantByDistance(float distance) {
        return null;
    }
    @Query("{cuisine:  '?0'}")
    ArrayList<Filter> findRestaurantByCuisine(String cuisine) {
        return null;
    }
    @Query("{status:  '?0'}")
    ArrayList<Filter> findRestaurantByStatus(String status) {
        return null;
    }
    @Query("{waiting:  '?0'}")
    ArrayList<Filter> findRestaurantByWaiting(String waiting) {
        return null;
    }
    @Query("{ratings:  '?0'}")
    ArrayList<Filter> findRestaurantByRatings(float ratings) {
        return null;
    }
    @Query("{alcohol:  '?0'}")
    ArrayList<Filter> findRestaurantByAlcohol(String alcohol) {
        return null;
    }
    @Query("{serviceOption:  '?0'}")
    ArrayList<Filter> findRestaurantByServiceOption(String serviceOption) {
        return null;
    }



}
