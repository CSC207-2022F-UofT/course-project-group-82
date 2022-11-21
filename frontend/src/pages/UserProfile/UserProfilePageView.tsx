import { NavigationState } from "@react-navigation/native";
import { SafeAreaView, Text } from "react-native";
import Navbar from "../../components/Navbar";

export function UserProfilePageView(props: {
    navigation: any;
    username: string;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    updateUsername: (username: string) => void;
    updateFirstName: (firstName: string) => void;
    updateLastName: (lastName: string) => void;
    updateEmail: (email: string) => void;
    updatePassword: (password: string) => void;
}) {
    return (
        <SafeAreaView className={"bg-[#fff] h-full w-full"}>
            <Navbar navigation={props.navigation} />
        </SafeAreaView>
    );
}
