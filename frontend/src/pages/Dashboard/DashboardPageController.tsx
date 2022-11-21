import { setItemAsync } from "expo-secure-store";
import { DashboardPageView } from "./DashboardPageView";
import { OuiRecommendations } from "./types/OuiRecommendations";

export function DashboardPageController(props: {
    userID: string | null;
    setUserID: (userID: string | null) => void;
    navigation: any;
    restaurantRecommendations: OuiRecommendations[];
    setRestaurantRecommendations: (
        recommendations: OuiRecommendations[]
    ) => void;
}) {
    async function doLogout() {
        props.setUserID(null);
        await setItemAsync("userToken", "");
    }

    function showNotificationsPage() {
        props.navigation.push("Notifications");
    }

    function findRestaurants(){
        props.navigation.push("FindRestaurants");
    }

    const viewProps = {
        userID: props.userID,
        doLogout,
        showNotificationsPage,
        recommendations: props.restaurantRecommendations,
        findRestaurants,
    };

    return <DashboardPageView {...viewProps} />;
}
