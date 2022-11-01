import BackgroundImage from "./assets/background.png";
import {
  ImageBackground,
  KeyboardAvoidingView,
  SafeAreaView,
  ScrollView,
  View,
} from "react-native";
import { useState } from "react";
import ErrorDisplay from "./components/ErrorDisplay";
import classNames from "classnames";
import UsernameInput from "./components/UsernameInput";
import PasswordInput from "./components/PasswordInput";
import { KeyboardAwareScrollView } from "react-native-ui-lib";
import EmailInput from "./components/EmailInput";
import FirstNameInput from "./components/FirstNameInput";
import LastNameInput from "./components/LastNameInput";
import FormCompletionInput from "./components/FormCompletionInput";
import LogoHeader from "../Login/components/LogoHeader";

export function RegisterPage() {
  const viewClassnames = classNames("w-full m-auto h-full self-center");
  const innerViewClassnames = classNames("justify-center h-full w-full");
  const formViewClassnames = classNames("w-3/4 m-auto");

  const [firstName, setFirstName] = useState<string>("");

  const [lastName, setLastName] = useState<string>("");

  function firstNameChange(text: string) {
    setFirstName(text);
  }

  function lastNameChange(text: string) {
    setLastName(text);
  }

  const [username, setUsername] = useState<string>("");

  function usernameChange(text: string) {
    setUsername(text);
  }

  const [password, setPassword] = useState<string>("");

  function passwordChange(text: string) {
    setPassword(text);
  }

  const [email, setEmail] = useState<string>("");

  function emailChange(text: string) {
    setEmail(text);
  }

  const [errorVisible, setErrorVisible] = useState<boolean>(false);
  const [errors, setErrors] = useState<string>("");

  function doRegister() {}

  return (
    <ImageBackground source={BackgroundImage}>
      <ErrorDisplay
        error={errors}
        errorVisible={errorVisible}
        setErrorVisible={setErrorVisible}
      />
      <SafeAreaView>
        <View className={viewClassnames}>
          <KeyboardAwareScrollView
            style={{ height: "100%", flex: 1 }}
            contentContainerStyle={{ flex: 1 }}
          >
            <View className={innerViewClassnames}>
              <View className={formViewClassnames}>
                <View className={"w-2/3 m-auto mb-5"}>
                  <LogoHeader />
                </View>
                <View className={"flex-row justify-around"}>
                  <FirstNameInput
                    firstName={firstName}
                    firstNameChange={firstNameChange}
                  />
                  <LastNameInput
                    lastName={lastName}
                    lastNameChange={lastNameChange}
                  />
                </View>
                <EmailInput email={email} emailChange={emailChange} />
                <UsernameInput
                  username={username}
                  usernameChange={usernameChange}
                />
                <PasswordInput
                  password={password}
                  passwordChange={passwordChange}
                />
                <FormCompletionInput doRegister={doRegister} />
              </View>
            </View>
          </KeyboardAwareScrollView>
        </View>
      </SafeAreaView>
    </ImageBackground>
  );
}
