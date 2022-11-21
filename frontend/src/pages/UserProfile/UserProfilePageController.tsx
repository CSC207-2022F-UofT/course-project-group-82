import { UserProfilePageView } from "./UserProfilePageView";
import * as ImagePicker from "expo-image-picker";
import * as FileSystem from "expo-file-system";
import { UpdateProfileInterface } from "../../services/User/UpdateProfileInterface";
import { doUpdateUserProfile } from "../../services/User/Update/UpdateUserProfile";

export function UserProfilePageController(props: {
    navigation: any;
    profilePictureLink: string;
    setProfilePictureLink: (profilePictureLink: string) => void;
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
    userID: string | null;
    setUserID: (userID: string | null) => void;
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

    async function pickImage() {
        let result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.All,
            allowsEditing: true,
            aspect: [3, 3],
            quality: 0.001,
        });

        if (!result.cancelled) {
            let base64 = await FileSystem.readAsStringAsync(result.uri, {
                encoding: "base64",
            });
            base64 = "data:image/jpeg;base64," + base64;
            props.setProfilePictureLink(base64);
        }
    }

    async function updateUserProfile() {
        const profile = {
            username: props.username.length == 0 ? undefined : props.username,
            firstName:
                props.firstName.length == 0 ? undefined : props.firstName,
            lastName: props.lastName.length == 0 ? undefined : props.lastName,
            email: props.email.length == 0 ? undefined : props.email,
            profilePictureLink:
                props.profilePictureLink.length == 0
                    ? undefined
                    : props.profilePictureLink,
            userId: props.userID,
        } as UpdateProfileInterface;
        const result = await doUpdateUserProfile(profile, props.setUserID);
    }

    const viewProps = {
        navigation: props.navigation,
        username: props.username,
        firstName: props.firstName,
        lastName: props.lastName,
        email: props.email,
        password: props.password,
        profilePictureLink: props.profilePictureLink,
        updateUsername,
        updateFirstName,
        updateLastName,
        updateEmail,
        updatePassword,
        pickImage,
        updateUserProfile,
    };

    return <UserProfilePageView {...viewProps} />;
}
