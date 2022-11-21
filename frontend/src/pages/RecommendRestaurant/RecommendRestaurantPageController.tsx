import React from "react";
import { RecommendRestaurantPageView } from "./RecommendRestaurantPageView";
import RecommendService from "../../services/User/Recommend";

export function RecommendRestaurantPageController(props: {
    restaurantName: string;
    setRestaurantName: React.Dispatch<React.SetStateAction<string>>;
    opinion: boolean;
    setOpinion: React.Dispatch<React.SetStateAction<boolean>>;
    // tags: Array<string>;
    // setTags: React.Dispatch<React.SetStateAction<Array<string>>>;
    errors: string;
    setErrors: React.Dispatch<React.SetStateAction<string>>;
    errorVisible: boolean;
    setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
    loading: boolean;
    setLoading: React.Dispatch<React.SetStateAction<boolean>>;

    // DESIGN CHOICE: tags not required (no minimum tags needed)
    // DESIGN CHOICE: opinion is to recommend restaurant by default. ideally, type Boolean with init value of null, but caused errors
}) {
    function restaurantNameChange(text: string) {
        props.setRestaurantName(text);
    }
    function opinionChange(input: boolean) {
        props.setOpinion(input);
    }
    /*
    function tagsChange(input: Array<string>) {
        props.setTags(input);
    }*/

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

    // CHECK: not sure if this is correctly linked to backend
    async function connectRecommendToBackend() {
        let response = await RecommendService(
            props.restaurantName,
            props.opinion,
            // props.tags
        );
        props.setErrorVisible(!!response);
        props.setErrors(response ? "" : "Could not create recommendation");

        if (response) {
            console.log("Recommendation created: " + response);
            props.navigation.navigate("Home");
        }
    }
    async function doRecommend() {
        if (!validateFormResponse()) return;
        props.setLoading(true);
        await connectRecommendToBackend();
        props.setLoading(false);
    }

    const viewProps = {
        restaurantNameChange,
        opinionChange,
        // tagsChange,
        doRecommend,
    };

    return <RecommendRestaurantPageView {...props} {...viewProps} />;
}
