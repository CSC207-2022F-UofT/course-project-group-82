import { useState } from "react";
import { RecommendRestaurantPageController } from "./RecommendRestaurantPageController";
export function RecommendRestaurantPageModel(props: { navigation: any }) {
    const [restaurantName, setRestaurantName] = useState<string>("");
    const [opinion, setOpinion] = useState<boolean>(true);
    const [tags, setTags] = useState<Array<string>>([]); // CHECK initial state
    const [errorVisible, setErrorVisible] = useState<boolean>(false);
    const [errors, setErrors] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);

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
    };

    return (
        <RecommendRestaurantPageController
            {...controllerProps}
        ></RecommendRestaurantPageController>
    );
}
