package com.ouieat.repository;


import ch.qos.logback.core.filter.Filter;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.lang.reflect.Array;
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
    @Query("{priceRange:  '?0'}")
    ArrayList<Filter> findRestaurantByPriceRange(Array[] PriceRange) {
        return null;
    }


}
