import { setItemAsync } from "expo-secure-store";
import { DashboardPageView } from "./DashboardPageView";
import { useCallback, useEffect } from "react";
import getAllRecommendationsService from "../../services/Recommendation/Get";
import getRestaurantByIdService from "../../services/Restaurants/GetById";
import { getUserDataFromIdService } from "../../services/User/GetById/GetById";
import { RecommendationInterface } from "../../services/Recommendation/RecommendationInterface";
import { RestaurantInterface } from "../../services/Restaurants/RestaurantInterface";
import { OuiRecommendations } from "../../data_types";

export function DashboardPageController(props: {
    userID: string | null;
    setUserID: (userID: string | null) => void;
    navigation: any;
    restaurantRecommendations: OuiRecommendations[];
    setRestaurantRecommendations: (
        recommendations: OuiRecommendations[]
    ) => void;
    refreshing: boolean;
    setRefreshing: (refreshing: boolean) => void;
}) {
    useEffect(() => {
        onRefresh();
    }, []);

    function findRestaurants() {
        props.navigation.push("FindRestaurants");
    }

    function makePosts() {
        props.navigation.push("MakePost");
    }

    const onRefresh = useCallback(async () => {
        props.setRefreshing(true);
        let ouiRecommendations = [] as OuiRecommendations[];
        let recommendationsData = await getAllRecommendationsService();
        recommendationsData = (recommendationsData as any)[
            "allRecommendations"
        ];
        const recommendations =
            recommendationsData as RecommendationInterface[];
        for (let i = recommendations.length - 1; i >= 0; i--) {
            let recommendation = recommendations[i];
            let restaurantData = await getRestaurantByIdService(
                recommendation.restaurantId
            );
            let restaurant = (restaurantData as any)[
                "restaurant"
            ] as RestaurantInterface;
            let userData = await getUserDataFromIdService(
                recommendation.userId
            );
            let user = (userData as any)["currentUserInfo"] as any;
            let address = "Undefined";
            if (restaurant.location) {
                address = restaurant.location.display_address.join(", ");
                address = address.replaceAll(", Canada", "");
            }
            ouiRecommendations.push({
                id: i,
                recommendedByName: user.firstName + " " + user.lastName,
                recommendedByUsername: user.username,
                recommendedByProfilePictureLink: user.profilePictureLink,
                timestamp: recommendation.postDate,
                recommends: recommendation.recommends,
                restaurantName: restaurant?.name || "Undefined",
                restaurantAddress: address,
                restaurantImageLink: restaurant.image_url,
                restaurantWebsiteLink: restaurant.url,
                restaurantForTags: recommendation.recommendationTags,
            });
        }
        props.setRestaurantRecommendations(ouiRecommendations);
        props.setRefreshing(false);
    }, []);

    const viewProps = {
        navigation: props.navigation,
        userID: props.userID,
        recommendations: props.restaurantRecommendations,
        findRestaurants,
        makePosts,
        onRefresh,
        refreshing: props.refreshing,
    };

    return <DashboardPageView {...viewProps} />;
}
