import React from "react";
import { RecommendRestaurantPageView } from "./RecommendRestaurantPageView";
import { setItemAsync } from "expo-secure-store";
import getByNameService from "../../services/Restaurants/GetByName";
import doPostRecommendationService from "../../services/Recommendation/Post";

export function RecommendRestaurantPageController(props: {
    navigation: any;
    restaurantName: string;
    setRestaurantName: React.Dispatch<React.SetStateAction<string>>;
    opinion: boolean;
    setOpinion: React.Dispatch<React.SetStateAction<boolean>>;
    errors: string;
    setErrors: React.Dispatch<React.SetStateAction<string>>;
    errorVisible: boolean;
    setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
    loading: boolean;
    setLoading: React.Dispatch<React.SetStateAction<boolean>>;
    userID: string | null;
    setUserID: (userID: string | null) => void;
    restaurants: Array<any>;
    setRestaurants: React.Dispatch<React.SetStateAction<Array<any>>>;
    selectedRestaurant: any[];
    setSelectedRestaurant: React.Dispatch<React.SetStateAction<any[]>>;
    modalVisible: boolean;
    setModalVisible: React.Dispatch<React.SetStateAction<boolean>>;
}) {
    function restaurantNameChange(text: string) {
        props.setRestaurantName(text);
    }
    function opinionChange(input: boolean) {
        props.setOpinion(input);
    }

    async function doLogout() {
        props.setUserID(null);
        await setItemAsync("userToken", "");
    }

    function showNotificationsPage() {
        props.navigation.push("Notifications");
    }

    function validateFormResponse(): boolean {
        let response = true;
        if (!props.restaurantName) {
            props.setErrors("Please select a restaurant");
            response = false;
        }
        if (!props.opinion) {
            props.setErrors(
                "Please indicate whether or not you recommend this restaurant"
            );
            response = false;
        }
        props.setErrorVisible(!response);
        return response;
    }

    function changeRestaurantSelected(restaurant: any[]) {
        props.setSelectedRestaurant(restaurant);
    }

    function openModal() {
        props.setModalVisible(true);
    }

    function closeModal() {
        props.setModalVisible(false);
    }

    async function updateSearchField(text: string) {
        props.setRestaurantName(text);
    }

    async function searchRestaurant() {
        console.log("searchRestaurant");
        props.setLoading(true);
        if (props.restaurantName.length === 0) {
            props.setRestaurants([]);
            props.setLoading(false);
            return;
        }
        let restaurants = await getByNameService(props.restaurantName);
        if (!restaurants) props.setRestaurants([]);
        else {
            restaurants = restaurants as any[];
            if (restaurants.length > 100) {
                restaurants = restaurants.slice(0, 100);
            }
            props.setRestaurants(restaurants as any[]);
        }
        props.setLoading(false);
    }

    async function recommendRestaurant() {
        const response = await doPostRecommendationService({
            userId: props.userID || "",
            restaurantId: (props.selectedRestaurant as any).id,
            recommends: props.opinion,
            postDate: new Date().toLocaleString(),
            rating: 0,
            review: "Empty review",
        });

        if (response) {
            props.navigation.navigate("Dashboard");
        } else {
            props.setErrors("Error recommending restaurant");
            props.setErrorVisible(true);
        }
    }

    const viewProps = {
        ...props,
        restaurantNameChange,
        opinionChange,
        doLogout,
        showNotificationsPage,
        restaurants: props.restaurants,
        selectedRestaurant: props.selectedRestaurant,
        changeRestaurantSelected,
        modalVisible: props.modalVisible,
        openModal,
        closeModal,
        updateSearchField,
        searchRestaurant,
        recommendRestaurant,
    };

    return <RecommendRestaurantPageView {...viewProps} />;
}
