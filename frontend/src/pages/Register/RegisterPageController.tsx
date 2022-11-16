import React from "react";
import RegisterService from "../../services/User/Register";
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

    const validateEmail = (email: string) => {
        return email.match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    };

    function validateFormResponse(): boolean {
        let response = true;

        if (
            !props.firstName ||
            !props.lastName ||
            !props.username ||
            !props.email ||
            !props.username ||
            !props.password
        ) {
            props.setErrors("Fill all values");
            response = false;
        }

        if (!validateEmail(props.email)) {
            props.setErrors("Enter a valid email address");
            response = false;
        }

        props.setErrorVisible(!response);
        return response;
    }

    async function connectRegisterToBackend() {
        let response = await RegisterService(
            props.firstName,
            props.lastName,
            props.email,
            props.username,
            props.password
        );
        props.setErrorVisible(!!response);
        props.setErrors(response ? "" : "Could not register user");

        if (response) {
            console.log("Registered user: " + response);
            props.navigation.navigate("Home");
        }
    }

    async function doRegister() {
        if (!validateFormResponse()) return;
        props.setLoading(true);
        await connectRegisterToBackend();
        props.setLoading(false);
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
