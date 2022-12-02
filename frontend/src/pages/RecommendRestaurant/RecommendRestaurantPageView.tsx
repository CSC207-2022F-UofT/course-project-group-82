import React from "react";
import { SafeAreaView, Text, View } from "react-native";
import { Button as RNButton } from "react-native-ui-lib";
import SelectedRestaurantPreview from "./components/SelectedRestaurantPreview";
import RestaurantNameFinder from "./components/RestaurantNameFinder";
import OpinionToggle from "./components/OpinionToggle";
import Navbar from "../../components/Navbar";
import { RestaurantInterface } from "../../services/Restaurants/RestaurantInterface";

export function RecommendRestaurantPageView(props: {
    navigation: any;
    restaurantName: string;
    restaurantNameChange: (text: string) => void;
    opinion: boolean;
    opinionChange: (input: boolean) => void;
    errors: string;
    errorVisible: boolean;
    setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
    loading: boolean;
    showNotificationsPage: () => void;
    doLogout: () => void;
    restaurants: Array<any>;
    selectedRestaurant: RestaurantInterface | null;
    changeRestaurantSelected: (item: RestaurantInterface) => void;
    modalVisible: boolean;
    openModal: () => void;
    closeModal: () => void;
    updateSearchField: (text: string) => void;
    searchRestaurant: () => void;
    recommendRestaurant: () => void;
}) {
    return (
        <SafeAreaView className={"h-full w-full"}>
            <View className={"bg-[#ffffff] flex flex-1 flex-col"}>
                <Navbar navigation={props.navigation} />
                {/* Share heading */}
                <View className={"flex flex-col p-5"}>
                    <Text className={"text-lg font-semibold"}>
                        Share a recommendation for...
                    </Text>

                    {/*Modal toggle and selected restaurant preview*/}
                    <SelectedRestaurantPreview {...props} />

                    {/* Modal component for searching restaurants*/}
                    <RestaurantNameFinder {...props} />

                    {/* Opinion Heading */}
                    <View className={"flex flex-col pt-10"}>
                        <Text className={"text-lg"}>
                            How did you like the restaurant?
                        </Text>
                        {/* Opinion selection change */}
                        <OpinionToggle
                            opinionChange={props.opinionChange}
                            opinion={props.opinion}
                        />
                    </View>
                </View>
            </View>

            {/*Share button*/}
            <View className={"flex flex-col"}>
                <View className={"flex flex-row justify-center"}>
                    <RNButton
                        onPress={props.recommendRestaurant}
                        borderRadius={10}
                        label={"Share"}
                        backgroundColor={"#FFB700"}
                    />
                </View>
            </View>
        </SafeAreaView>
    );
}
