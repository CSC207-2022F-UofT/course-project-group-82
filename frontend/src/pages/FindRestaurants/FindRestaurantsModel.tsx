import { FindRestaurantsController } from "./FindRestaurantsController";
import React, { useContext, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { AllCategories } from "../../categories";

export function FindRestaurantsModel(props: { navigation: any }) {
    const { userID, setUserID } = useContext(UserContext);
    const [restaurantInput, setRestaurantInput] = useState<string>("");
    const [expanded, setExpanded] = React.useState<boolean>(true);

    const [selectedCuisines, setSelectedCuisines] = React.useState<any[]>([]);

    const controllerProps = {
        ...props,
        userID,
        setUserID,
        restaurantInput,
        setRestaurantInput,
        expanded,
        setExpanded,
        searchItems: AllCategories,
        selectedCuisines,
        setSelectedCuisines,
    };

    return <FindRestaurantsController {...controllerProps} />;
}
