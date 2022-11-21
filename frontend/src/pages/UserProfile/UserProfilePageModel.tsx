import React, { useState } from "react";
import { NavigationState } from "@react-navigation/native";
import { UserProfilePageController } from "./UserProfilePageController";

export function UserProfilePageModel(props: { navigation: NavigationState }) {
    const [username, setUsername] = useState<string>("");
    const [firstName, setFirstName] = useState<string>("");
    const [lastName, setLastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const controllerProps = {
        ...props,
        username,
        setUsername,
        firstName,
        setFirstName,
        lastName,
        setLastName,
        email,
        setEmail,
        password,
        setPassword,
    };

    return <UserProfilePageController {...controllerProps} />;
}
