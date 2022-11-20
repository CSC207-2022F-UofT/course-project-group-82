import React, { useContext, useState } from "react";
import { UserContext } from "../../context/UserContext";
import loginService from "../../services/User/Login";
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
    loading: boolean;
    setLoading: React.Dispatch<React.SetStateAction<boolean>>;
}) {
    function usernameChange(text: string) {
        props.setUsername(text);
    }

    function passwordChange(text: string) {
        props.setPassword(text);
    }

    const { setUserID } = useContext(UserContext);

    function validateFormResponse(): boolean {
        let response = true;

        if (!props.username || !props.password) {
            response = false;
            props.setErrors("Fill all values");
        }

        props.setErrorVisible(!response);
        return response;
    }

    async function connectLoginToBackend() {
        let response = await loginService(props.username, props.password);
        props.setErrorVisible(!!response);
        props.setErrors(response ? "" : "Invalid username or password");

        if (response) {
            console.log("Logged in user: " + response);
            setUserID(typeof response === "string" ? response : "--error--");
            if (typeof response !== "string")
                console.error(
                    "Response id sent back is not a string: " + response
                );
        }
    }

    async function doLogin() {
        if (!validateFormResponse()) return;

        props.setLoading(true);
        await connectLoginToBackend();
        props.setLoading(false);
    }

    const viewProps = {
        usernameChange,
        passwordChange,
        doLogin,
    };

    return <LoginPageView {...props} {...viewProps} />;
}
