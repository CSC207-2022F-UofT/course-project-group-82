import {
    ImageBackground,
    KeyboardAvoidingView,
    SafeAreaView,
    ScrollView,
    View,
    Text,
} from "react-native";
import React, { useState } from "react";
import RestaurantNameInput from "./components/RestaurantNameInput";
import OpinionButton from "./components/OpinionButton";
// import TagsMultiselect from "./components/TagsMultiselect";
import { KeyboardAwareScrollView, Modal } from "react-native-ui-lib";


export function RecommendRestaurantPageView(props: {
    restaurantName: string;
    restaurantNameChange: (text: string) => void;
    opinion: boolean;
    opinionChange: (input: boolean) => void;
    tags: Array<string>;
    tagsChange: (input: boolean) => void;
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
                    opinionChange={props.opinionChange}/>

            </View>
        )
    }
}
