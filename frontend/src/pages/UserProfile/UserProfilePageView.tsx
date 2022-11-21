import { NavigationState } from "@react-navigation/native";
import { SafeAreaView, Text } from "react-native";

export function UserProfilePageView(props: {
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
        <SafeAreaView className={"bg-[#fff]"}>
            <Text>User profile page</Text>
        </SafeAreaView>
    );
}
