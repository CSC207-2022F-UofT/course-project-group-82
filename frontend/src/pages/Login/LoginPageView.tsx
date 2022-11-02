import classNames from "classnames";
import { View, ImageBackground } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import React from "react";
import BackgroundImage from "./assets/background.png";
import LogoHeader from "./components/LogoHeader";
import UsernameInput from "./components/UsernameInput";
import PasswordInput from "./components/PasswordInput";
import FormCompletionInput from "./components/FormCompletionInput";
import ErrorDisplay from "./components/ErrorDisplay";

export function LoginPageView(props: {
  username: string;
  usernameChange: (text: string) => void;
  password: string;
  passwordChange: (text: string) => void;
  errors: string;
  setErrors: React.Dispatch<React.SetStateAction<string>>;
  errorVisible: boolean;
  setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
  doLogin: () => void;
}) {
  const viewClassnames = classNames("w-1/2 m-auto h-full");

  const innerViewClassnames = classNames("justify-center h-full w-full");

  const formViewClassnames = classNames("mt-5");

  return (
    <ImageBackground source={BackgroundImage}>
      <ErrorDisplay
        error={props.errors}
        errorVisible={props.errorVisible}
        setErrorVisible={props.setErrorVisible}
      />
      <SafeAreaView className={"bg-[#f5]"}>
        <View className={viewClassnames}>
          <View className={innerViewClassnames}>
            <LogoHeader />
            <View className={formViewClassnames}>
              <UsernameInput
                username={props.username}
                usernameChange={props.usernameChange}
              />
              <PasswordInput
                password={props.password}
                passwordChange={props.passwordChange}
              />
              <FormCompletionInput doLogin={props.doLogin} />
            </View>
          </View>
        </View>
      </SafeAreaView>
    </ImageBackground>
  );
}
