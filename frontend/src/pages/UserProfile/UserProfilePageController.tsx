import { UserProfilePageView } from "./UserProfilePageView";
import { NavigationState } from "@react-navigation/native";

export function UserProfilePageController(props: {
    navigation: NavigationState;
    username: string;
    setUsername: (username: string) => void;
    firstName: string;
    setFirstName: (firstName: string) => void;
    lastName: string;
    setLastName: (lastName: string) => void;
    email: string;
    setEmail: (email: string) => void;
    password: string;
    setPassword: (password: string) => void;
}) {
    function updateUsername(username: string) {
        props.setUsername(username);
    }

    function updateFirstName(firstName: string) {
        props.setFirstName(firstName);
    }

    function updateLastName(lastName: string) {
        props.setLastName(lastName);
    }

    function updateEmail(email: string) {
        props.setEmail(email);
    }

    function updatePassword(password: string) {
        props.setPassword(password);
    }

    const viewProps = {
        username: props.username,
        firstName: props.firstName,
        lastName: props.lastName,
        email: props.email,
        password: props.password,
        updateUsername,
        updateFirstName,
        updateLastName,
        updateEmail,
        updatePassword,
    };

    return <UserProfilePageView {...viewProps} />;
}
