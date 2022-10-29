import classNames from "classnames";
import { Text, View, StyleSheet, ImageBackground } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { useState } from "react";
import { TextField, Image, Button, Colors } from "react-native-ui-lib";
import BackgroundImage from "./assets/background.png";
import LogoHeader from "./components/LogoHeader";
import UsernameInput from "./components/UsernameInput";
import PasswordInput from "./components/PasswordInput";
import FormCompletionInput from "./components/FormCompletionInput";
import ErrorDisplay from "./components/ErrorDisplay";

export function LoginPage() {
  const viewClassnames = classNames("w-1/2 m-auto h-full");

  const innerViewClassnames = classNames("justify-center h-full w-full");

  const formViewClassnames = classNames("mt-5");

  const [username, setUsername] = useState<string>("");

  function usernameChange(text: string) {
    setUsername(text);
  }

  const [password, setPassword] = useState<string>("");

  function passwordChange(text: string) {
    setPassword(text);
  }

  const [errorVisible, setErrorVisible] = useState<boolean>(false);
  const [errors, setErrors] = useState<string>("");

  function doLogin() {}

  return (
    <ImageBackground source={BackgroundImage}>
      <SafeAreaView className={"bg-[#f5]"}>
        <View className={viewClassnames}>
          <View className={innerViewClassnames}>
            <LogoHeader />
            <View className={formViewClassnames}>
              <UsernameInput
                username={username}
                usernameChange={usernameChange}
              />
              <PasswordInput
                password={password}
                passwordChange={passwordChange}
              />
              <ErrorDisplay error={errors} errorVisible={errorVisible} />
              <FormCompletionInput doLogin={doLogin} />
            </View>
          </View>
        </View>
      </SafeAreaView>
    </ImageBackground>
  );
}
