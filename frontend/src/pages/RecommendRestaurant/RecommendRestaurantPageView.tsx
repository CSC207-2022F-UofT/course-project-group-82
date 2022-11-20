import {
    ImageBackground,
    KeyboardAvoidingView,
    SafeAreaView,
    ScrollView,
    View,
    Text,
} from "react-native";
import React, { useState } from "react";
import ErrorDisplay from "./components/ErrorDisplay";
import classNames from "classnames";
import UsernameInput from "./components/UsernameInput";
import PasswordInput from "./components/PasswordInput";
import { KeyboardAwareScrollView, Modal } from "react-native-ui-lib";
import EmailInput from "./components/EmailInput";
import FirstNameInput from "./components/FirstNameInput";
import LastNameInput from "./components/LastNameInput";
import FormCompletionInput from "./components/FormCompletionInput";
import LogoHeader from "../Login/components/LogoHeader";

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


}