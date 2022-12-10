package com.ouieat.restaurant;

import static org.mockito.Mockito.when;

import com.ouieat.OuiLogger;
import com.ouieat.handler.ServiceTest;
import com.ouieat.interactor.restaurant.RestaurantInteractor;
import com.ouieat.models.restaurant.Category;
import com.ouieat.models.restaurant.Coordinates;
import com.ouieat.models.restaurant.Location;
import com.ouieat.models.restaurant.Restaurant;
import com.ouieat.requests.restaurant.AuthenticatedRestaurantRequests;
import com.ouieat.requests.restaurant.UnauthenticatedRestaurantRequest;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantServiceTest
    extends ServiceTest<RestaurantInteractor, AuthenticatedRestaurantRequests, UnauthenticatedRestaurantRequest> {

    public RestaurantServiceTest() {
        super(
            RestaurantInteractor.class,
            AuthenticatedRestaurantRequests.class,
            UnauthenticatedRestaurantRequest.class
        );
        OuiLogger.log(Level.DEBUG, "Testing Restaurant Service Requests");
    }

    @AfterAll
    public void teardown() {
        OuiLogger.log(
            Level.DEBUG,
            "Finished Testing Restaurant Service Requests"
        );
    }

    // Route: /getRestaurantsByName

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
    }
}
