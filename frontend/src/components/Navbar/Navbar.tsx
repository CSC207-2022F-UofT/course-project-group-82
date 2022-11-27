import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { Avatar, IconButton } from "react-native-paper";
import { useCallback, useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { setItemAsync } from "expo-secure-store";

import SimpleIcon from "./assets/SimpleIcon.png";
import { getUserDataFromIdService } from "../../services/User/GetById/GetById";

export function Navbar(props: { navigation: any }) {
    const { userID, setUserID } = useContext(UserContext);
    const [profilePictureLink, setProfilePictureLink] = useState<string>("");

    useEffect(() => {
        grabDashboardData();
    }, []);

    const grabDashboardData = useCallback(async () => {
        if (userID) {
            let userData = await getUserDataFromIdService(userID);
            let user = (userData as any)["currentUserInfo"] as any;
            setProfilePictureLink(user.profilePictureLink);
        }
    }, [props.navigation]);

    async function doLogout() {
        setUserID(null);
        await setItemAsync("userToken", "");
    }

    function showDashboardPage() {
        props.navigation.navigate("Dashboard");
    }

    function showNotificationsPage() {
        props.navigation.navigate("Notifications");
    }

    function showUserProfilePage() {
        props.navigation.navigate("UserProfile");
    }

    const styles = StyleSheet.create({
        withUnderline: {
            display: "flex",
            flexDirection: "row",
            backgroundColor: "#FFB700",
            borderRadius: 20,
            padding: 10,
            shadowOpacity: 0.5,
            shadowRadius: 1,
            shadowOffset: {
                height: 0,
                width: 0,
            },
        },
        withIcon: {
            color: "#000000",
            backgroundColor: "#FFDC25",
            shadowOpacity: 1,
            shadowRadius: 1,
            shadowOffset: {
                height: 0,
                width: 0,
            },
        },
        withUserIcon: {
            backgroundColor: "transparent",
        },
    });

    return (
        <View className={"bg-[#ffb700] h-16"}>
            <View className={"h-full w-full justify-center px-3"}>
                <View
                    className={
                        "flex flex-row items-center gap-2 w-full justify-between"
                    }
                >
                    <TouchableOpacity onPress={showDashboardPage}>
                        <View className={"flex flex-row items-center"}>
                            <Image
                                source={SimpleIcon}
                                resizeMode={"center"}
                                className={"h-10 w-10"}
                            />
                            <Text className={"text-2xl font-bold"}>
                                Oui-eat
                            </Text>
                        </View>
                    </TouchableOpacity>
                    <View
                        className={
                            "flex flex-row justify-end gap-x-2 items-center"
                        }
                    >
                        <IconButton
                            onPress={showNotificationsPage}
                            style={styles.withIcon}
                            size={18}
                            icon="bell-outline"
                        />
                        <IconButton
                            onPress={doLogout}
                            style={styles.withIcon}
                            size={18}
                            icon="logout"
                        />
                        <TouchableOpacity onPress={showUserProfilePage}>
                            <View className={"shadow-lg rounded-full"}>
                                <Avatar.Image
                                    style={styles.withUserIcon}
                                    size={32}
                                    source={{
                                        uri:
                                            profilePictureLink ||
                                            "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
                                    }}
                                />
                            </View>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </View>
    );
}
