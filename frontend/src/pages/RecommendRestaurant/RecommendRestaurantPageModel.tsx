import React, { useContext, useRef, useState } from "react";
import { RecommendRestaurantPageController } from "./RecommendRestaurantPageController";
import { UserContext } from "../../context/UserContext";
import { RestaurantInterface } from "../../services/Restaurants/RestaurantInterface";
export function RecommendRestaurantPageModel(props: { navigation: any }) {
    const [restaurantName, setRestaurantName] = useState<string>("");
    const [opinion, setOpinion] = useState<boolean>(true);
    const [tags, setTags] = useState<Array<string>>([]); // CHECK initial state
    const [errorVisible, setErrorVisible] = useState<boolean>(false);
    const [errors, setErrors] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);
    const { userID, setUserID } = useContext(UserContext);
    const [modalVisible, setModalVisible] = useState<boolean>(false);
    const [restaurants, setRestaurants] = useState<Array<any>>([]);
    const [selectedRestaurant, setSelectedRestaurant] =
        useState<RestaurantInterface | null>(null);

    const controllerProps = {
        restaurantName,
        setRestaurantName,
        opinion,
        setOpinion,
        tags,
        setTags,
        errorVisible,
        setErrorVisible,
        errors,
        setErrors,
        loading,
        setLoading,
        userID,
        setUserID,
        navigation: props.navigation,
        restaurants,
        setRestaurants,
        selectedRestaurant,
        setSelectedRestaurant,
        modalVisible,
        setModalVisible,
    };

    return <RecommendRestaurantPageController {...controllerProps} />;
}
