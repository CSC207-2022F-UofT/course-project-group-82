import React, { useContext, useState } from "react";
import { UserProfilePageController } from "./UserProfilePageController";
import { UserContext } from "../../context/UserContext";

export function UserProfilePageModel(props: { navigation: any }) {
    const { userID, setUserID } = useContext(UserContext);
    const [profilePictureLink, setProfilePictureLink] = useState<string>("");
    const [username, setUsername] = useState<string>("");
    const [firstName, setFirstName] = useState<string>("");
    const [lastName, setLastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const controllerProps = {
        ...props,
        profilePictureLink,
        setProfilePictureLink,
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
        userID,
        setUserID,
    };

    return <UserProfilePageController {...controllerProps} />;
}
