import { useContext, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { OuiRecommendations } from "../../data_types";
import { DashboardPageController } from "./DashboardPageController";

export function DashboardPageModel(props: { navigation: any }) {
    const { userID, setUserID } = useContext(UserContext);
    const [restaurantRecommendations, setRestaurantRecommendations] = useState<
        OuiRecommendations[]
    >([]);
    const [refreshing, setRefreshing] = useState(false);

    const controllerProps = {
        ...props,
        userID,
        setUserID,
        restaurantRecommendations,
        setRestaurantRecommendations,
        refreshing,
        setRefreshing,
    };

    return <DashboardPageController {...controllerProps} />;
}
