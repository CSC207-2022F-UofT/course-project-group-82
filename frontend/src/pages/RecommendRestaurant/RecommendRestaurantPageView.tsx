import React from "react";
import { SafeAreaView, ScrollView, Text, View } from "react-native";
import {
    Button as RNButton,
    KeyboardAwareScrollView,
} from "react-native-ui-lib";
import SelectedRestaurantPreview from "./components/SelectedRestaurantPreview";
import RestaurantNameFinder from "./components/RestaurantNameFinder";
import OpinionToggle from "./components/OpinionToggle";
import Navbar from "../../components/Navbar";
import OpinionTags from "./components/OpinionTags";

import { RestaurantInterface } from "../../services/Restaurants/RestaurantInterface";
import OpinionText from "./components/OpinionText";

export function RecommendRestaurantPageView(props: {
    navigation: any;
    restaurantName: string;
    restaurantNameChange: (text: string) => void;
    opinion: boolean;
    opinionChange: (input: boolean) => void;
    opinionText: string;
    opinionTextChange: (text: string) => void;
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
    selectedOpinions: Array<number>;
    setSelectedOpinions: React.Dispatch<React.SetStateAction<Array<number>>>;
}) {
    return (
        <SafeAreaView className={"h-full w-full"}>
            <View className={"bg-[#ffffff] flex flex-1 flex-col"}>
                <Navbar navigation={props.navigation} />
                {/* Share heading */}
                <KeyboardAwareScrollView className={"flex flex-col p-5"}>
                    <View className={"flex flex-col pb-3"}>
                        <Text className={"text-xl"}>Recommend</Text>
                        <View className={"w-1/2 bg-[#ffb700] h-1"} />
                    </View>

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

                    {/* Opinion Tags Heading */}
                    <View className={"flex flex-col pt-10"}>
                        <Text className={"text-lg"}>
                            What is this recommendation for?
                        </Text>
                        {/* Opinion selection change */}
                        <OpinionTags
                            selectedRestaurant={props.selectedRestaurant}
                            selectedOpinions={props.selectedOpinions}
                            setSelectedOpinions={props.setSelectedOpinions}
                        />
                    </View>

                    {/* Other errors */}
                    <View className={"flex flex-col pt-10"}>
                        <View className={"flex flex-row items-center"}>
                            <Text className={"text-lg"}>
                                Other recommendations?
                            </Text>
                            <Text className={"text-sm text-gray-600"}>
                                (Separate with commas)
                            </Text>
                        </View>
                        {/* Change recommendations */}
                        <OpinionText
                            opinionText={props.opinionText}
                            opinionTextChange={props.opinionTextChange}
                        />
                    </View>
                </KeyboardAwareScrollView>
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
