import BackgroundImage from "./assets/background.png";
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
import RegisterService from "../../services/User/Register";

export function RegisterPageView(props: {
  firstName: string;
  firstNameChange: (text: string) => void;
  lastName: string;
  lastNameChange: (text: string) => void;
  email: string;
  emailChange: (text: string) => void;
  username: string;
  usernameChange: (text: string) => void;
  password: string;
  passwordChange: (text: string) => void;
  doRegister: () => void;
  errors: string;
  errorVisible: boolean;
  setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
  loading: boolean;
}) {
  const viewClassnames = classNames("w-full m-auto h-full self-center");
  const innerViewClassnames = classNames("justify-center h-full w-full");
  const formViewClassnames = classNames("w-3/4 m-auto");

  function formView() {
    return (
      <>
        <View className={"flex-row justify-around"}>
          <FirstNameInput
            firstName={props.firstName}
            firstNameChange={props.firstNameChange}
          />
          <LastNameInput
            lastName={props.lastName}
            lastNameChange={props.lastNameChange}
          />
        </View>
        <EmailInput email={props.email} emailChange={props.emailChange} />
        <UsernameInput
          username={props.username}
          usernameChange={props.usernameChange}
        />
        <PasswordInput
          password={props.password}
          passwordChange={props.passwordChange}
        />
        <FormCompletionInput doRegister={props.doRegister} />
      </>
    );
  }

  return (
    <ImageBackground source={BackgroundImage}>
      <ErrorDisplay
        error={props.errors}
        errorVisible={props.errorVisible}
        setErrorVisible={props.setErrorVisible}
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
                {props.loading ? (
                  <Text className={"text-center"}>Loading...</Text>
                ) : (
                  formView()
                )}
              </View>
            </View>
          </KeyboardAwareScrollView>
        </View>
      </SafeAreaView>
    </ImageBackground>
  );
}
