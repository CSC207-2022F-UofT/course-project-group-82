import {
    ImageBackground,
    KeyboardAvoidingView,
    SafeAreaView,
    ScrollView,
    View,
    Text,
} from "react-native";
import React from "react";
import RestaurantNameInput from "./components/RestaurantNameInput";
import OpinionButton from "./components/OpinionButton";
import FormCompletionInput from "../RecommendRestaurant/components/FormCompletionInput";

export function RecommendRestaurantPageView(props: {
    restaurantName: string;
    restaurantNameChange: (text: string) => void;
    opinion: boolean;
    opinionChange: (input: boolean) => void;
    doRecommend: () => void;
    errors: string;
    errorVisible: boolean;
    setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
    loading: boolean;
}) {
    function formView() {
        return (
            <View>
                <RestaurantNameInput
                    restaurantName={props.restaurantName}
                    restaurantNameChange={props.restaurantNameChange}
                />

                <OpinionButton
                    opinion={props.opinion}
                    opinionChange={props.opinionChange}
                />
                <FormCompletionInput doRecommend={props.doRecommend} />
            </View>
        );
    }

    return <SafeAreaView>{formView()}</SafeAreaView>;
}
