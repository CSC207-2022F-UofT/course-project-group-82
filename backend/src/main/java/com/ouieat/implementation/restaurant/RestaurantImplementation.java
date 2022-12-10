package com.ouieat.implementation.restaurant;

import com.ouieat.implementation.handler.Implementation;
import com.ouieat.implementation.restaurant.methods.GetRestaurantsById;
import com.ouieat.implementation.restaurant.methods.GetRestaurantsByName;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.responses.handler.Response;
import org.springframework.stereotype.Service;

@Service
public class RestaurantImplementation
    extends Implementation<RestaurantInteractor> {

    public RestaurantImplementation(
        UserInteractor userInteractor,
        RestaurantInteractor interactor
    ) {
        super(userInteractor, interactor);
    }

    // Find restaurants by name
    public Response getRestaurantsByName(String name) {
        return GetRestaurantsByName.apply(this.interactor, name);
    }

    // Find restaurant by id
    public Response getRestaurantById(String id) {
        return GetRestaurantsById.apply(this.interactor, id);
    }
}
