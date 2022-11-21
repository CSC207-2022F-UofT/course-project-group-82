import { FindRestaurantsView } from "./FindRestaurantsView";
import { setItemAsync } from "expo-secure-store";

export function FindRestaurantsController(props: {
    navigation: any;
    userID: string | null;
    setUserID: (text: string | null) => void;
    restaurantInput: string;
    setRestaurantInput: (text: string) => void;
    expanded: boolean;
    setExpanded: (state: boolean) => void;
    searchItems: any;
    selectedCuisines: any[];
    setSelectedCuisines: any;
}) {
    function updateRestaurantInput(input: string) {
        props.setRestaurantInput(input);
    }

    async function doLogout() {
        props.setUserID(null);
        await setItemAsync("userToken", "");
    }

    function showNotificationsPage() {
        props.navigation.push("Notifications");
    }

    function changeOfCuisinesSelected(selectedCuisines: any[]) {
        props.setSelectedCuisines(selectedCuisines);
    }

    const handlePress = () => props.setExpanded(!props.expanded);
    const viewProps = {
        navigation: props.navigation,
        userID: props.userID,
        doLogout,
        showNotificationsPage,
        restaurantInput: props.restaurantInput,
        updateRestaurantInput,
        handlePress,
        expanded: props.expanded,
        searchItems: props.searchItems,
        selectedCuisines: props.selectedCuisines,
        changeOfCuisinesSelected,
    };

    return <FindRestaurantsView {...viewProps} />;
}
