package com.ouieat.restaurant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.ouieat.ServiceTest;
import com.ouieat.implementation.restaurant.RestaurantImplementation;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.models.restaurant.Category;
import com.ouieat.models.restaurant.Coordinates;
import com.ouieat.models.restaurant.Location;
import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.requests.restaurant.AuthenticatedRestaurantRequests;
import com.ouieat.requests.restaurant.UnauthenticatedRestaurantRequest;
import com.ouieat.responses.handler.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RestaurantServiceTest
    extends ServiceTest<RestaurantInteractor, AuthenticatedRestaurantRequests, UnauthenticatedRestaurantRequest> {

    public RestaurantServiceTest() {
        super(
            RestaurantInteractor.class,
            AuthenticatedRestaurantRequests.class,
            UnauthenticatedRestaurantRequest.class,
            RestaurantImplementation.class
        );
    }

    // Route: /getRestaurantsByName

    @Test
    public void getRestaurantsByNameNull() {
        when(interactor.findRestaurantByName(null)).thenReturn(null);
        Response response = unauthenticatedRequest.handle(
            null,
            unauthenticatedRequest.getRestaurantsByName
        );
        assertThat(response.status).isEqualTo("error");
    }

    @Test
    public void getRestaurantsByNameSuccess() {
        when(interactor.findRestaurantByName("name"))
            .thenReturn(
                new ArrayList<>(
                    List.of(
                        new Restaurant(
                            "name",
                            "address",
                            "phone",
                            "website",
                            14,
                            new Category[] { new Category("asd", "ad") },
                            3.2,
                            new Coordinates(1, 2),
                            "$",
                            new Location(
                                "city",
                                "state",
                                "country",
                                "zip",
                                "address",
                                "address2",
                                "address3",
                                new String[] { "address4" }
                            ),
                            "description",
                            "hours"
                        )
                    )
                )
            );

        Response response = unauthenticatedRequest.handle(
            "name",
            unauthenticatedRequest.getRestaurantsByName
        );
        assertThat(response.status).isEqualTo("success");
        assertThat(response.responseData.data).isNotNull();
    }
}
