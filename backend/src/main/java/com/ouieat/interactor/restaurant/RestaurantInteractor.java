package com.ouieat.interactor.restaurant;

import com.ouieat.interactor.Interactor;
import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.repository.RestaurantRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantInteractor
    extends Interactor<Restaurant, RestaurantRepository> {

    @Autowired
    public RestaurantInteractor(RestaurantRepository restaurantRepository) {
        super(restaurantRepository);
    }

    public ArrayList<Restaurant> findRestaurantByName(String name) {
        ArrayList<Restaurant> restaurants = new ArrayList<>(
            repository.findAll()
        );
        ArrayList<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (
                restaurant.getName().toLowerCase().contains(name.toLowerCase())
            ) {
                result.add(restaurant);
            }
        }
        return result;
    }
}
