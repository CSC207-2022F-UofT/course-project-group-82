import React from "react";
import { RecommendRestaurantPageView } from "./RecommendRestaurantPageView";
import { setItemAsync } from "expo-secure-store";
import getByNameService from "../../services/Restaurants/GetByName";
import doPostRecommendationService from "../../services/Recommendation/Post";
import { RestaurantInterface } from "../../services/Restaurants/RestaurantInterface";

export function RecommendRestaurantPageController(props: {
    navigation: any;
    restaurantName: string;
    setRestaurantName: React.Dispatch<React.SetStateAction<string>>;
    opinion: boolean;
    setOpinion: React.Dispatch<React.SetStateAction<boolean>>;
    opinionText: string;
    setOpinionText: React.Dispatch<React.SetStateAction<string>>;
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
    selectedRestaurant: RestaurantInterface | null;
    setSelectedRestaurant: React.Dispatch<
        React.SetStateAction<RestaurantInterface | null>
    >;
    selectedOpinions: Array<number>;
    setSelectedOpinions: React.Dispatch<React.SetStateAction<Array<number>>>;
    modalVisible: boolean;
    setModalVisible: React.Dispatch<React.SetStateAction<boolean>>;
}) {
    function restaurantNameChange(text: string) {
        props.setRestaurantName(text);
    }
    function opinionChange(input: boolean) {
        props.setOpinion(input);
    }

    function opinionTextChange(text: string) {
        props.setOpinionText(text);
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

    function changeRestaurantSelected(restaurant: RestaurantInterface) {
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
        let recommendationTags = [] as Array<string>;
        if (props.opinionText) {
            let tempTags = props.opinionText.split(",");
            tempTags.forEach((tag) => {
                if (tag.trim()) recommendationTags.push(tag.trim());
            });
        }
        if (props.selectedOpinions.length > 0) {
            props.selectedOpinions.forEach((opinion) => {
                if (!props.selectedRestaurant) {
                    console.log("Error: selectedRestaurant is null");
                    return;
                }
                recommendationTags.push(
                    props.selectedRestaurant.categories[opinion].title
                );
            });
        }
        const response = await doPostRecommendationService({
            userId: props.userID || "",
            restaurantId: (props.selectedRestaurant as any).id,
            recommends: props.opinion,
            recommendationTags: recommendationTags,
            postDate: new Date().toDateString(),
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
        opinionTextChange,
        opinionText: props.opinionText,
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
        selectedOpinions: props.selectedOpinions,
        setSelectedOpinions: props.setSelectedOpinions,
    };

    return <RecommendRestaurantPageView {...viewProps} />;
}
