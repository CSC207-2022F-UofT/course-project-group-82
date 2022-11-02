import React, { useContext, useState } from "react";
import { UserContext } from "../../context/UserContext";
import LoginService from "../../services/User/Login";
import { LoginPageView } from "./LoginPageView";

export function LoginPageController(props: {
  username: string;
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  password: string;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
  errorVisible: boolean;
  setErrorVisible: React.Dispatch<React.SetStateAction<boolean>>;
  errors: string;
  setErrors: React.Dispatch<React.SetStateAction<string>>;
}) {
  function usernameChange(text: string) {
    props.setUsername(text);
  }

  function passwordChange(text: string) {
    props.setPassword(text);
  }

  const { setUserID } = useContext(UserContext);

  async function doLogin() {
    let response = await LoginService(props.username, props.password);
    if (response) {
      props.setErrorVisible(false);
      props.setErrors("");
      console.log("Logged in user: " + response);
      setUserID(typeof response === "string" ? response : "--error--");
    } else {
      props.setErrors("Invalid username or password");
      props.setErrorVisible(true);
    }
  }

  const viewProps = {
    usernameChange,
    passwordChange,
    doLogin,
  };

  return <LoginPageView {...props} {...viewProps} />;
}
