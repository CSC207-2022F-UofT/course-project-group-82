import React, { useState } from "react";
import RegisterService from "../../services/User/Register";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RegisterPageView } from "./RegisterPageView";

export function RegisterPageController(props: {
  navigation: any;
  firstName: string;
  setFirstName: React.Dispatch<React.SetStateAction<string>>;
  lastName: string;
  setLastName: React.Dispatch<React.SetStateAction<string>>;
  email: string;
  setEmail: React.Dispatch<React.SetStateAction<string>>;
  username: string;
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  password: string;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
  errors: string;
  setErrors: React.Dispatch<React.SetStateAction<string>>;
  errorVisible: boolean;
  setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
  loading: boolean;
  setLoading: React.Dispatch<React.SetStateAction<boolean>>;
}) {
  function firstNameChange(text: string) {
    props.setFirstName(text);
  }

  function lastNameChange(text: string) {
    props.setLastName(text);
  }

  function usernameChange(text: string) {
    props.setUsername(text);
  }

  function passwordChange(text: string) {
    props.setPassword(text);
  }

  function emailChange(text: string) {
    props.setEmail(text);
  }

  async function doRegister() {
    props.setLoading(true);
    let response = await RegisterService(
      props.firstName,
      props.lastName,
      props.email,
      props.username,
      props.password
    );
    if (response) {
      props.setErrorVisible(false);
      props.setErrors("");
      console.log("Registered user: " + response);
      props.setLoading(false);
      props.navigation.navigate("Home");
    } else {
      props.setErrors("Could not register user");
      props.setErrorVisible(true);
      props.setLoading(false);
    }
  }

  const viewProps = {
    firstNameChange,
    lastNameChange,
    emailChange,
    usernameChange,
    passwordChange,
    doRegister,
  };

  return <RegisterPageView {...props} {...viewProps} />;
}
