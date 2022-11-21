import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { Avatar, IconButton } from "react-native-paper";
import { useContext } from "react";
import { UserContext } from "../../context/UserContext";
import { setItemAsync } from "expo-secure-store";

import UserIcon from "./assets/UserIcon.png";
import SimpleIcon from "./assets/SimpleIcon.png";

export function Navbar(props: { navigation: any }) {
    const { setUserID } = useContext(UserContext);

    async function doLogout() {
        setUserID(null);
        await setItemAsync("userToken", "");
    }

    function showNotificationsPage() {
        props.navigation.push("Notifications");
    }

    function showUserProfilePage() {
        props.navigation.push("UserProfile");
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
        <View className={"bg-[#ffb700] h-16 shadow-md"}>
            <View className={"h-full w-full justify-center px-3"}>
                <View
                    className={
                        "flex flex-row items-center gap-2 w-full justify-between"
                    }
                >
                    <View className={"flex flex-row items-center"}>
                        <Image
                            source={SimpleIcon}
                            resizeMode={"center"}
                            className={"h-10 w-10"}
                        />
                        <Text className={"text-2xl font-bold"}>Oui-eat</Text>
                    </View>

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
                            <View className={"p-1 border rounded-full"}>
                                <Avatar.Image
                                    style={styles.withUserIcon}
                                    size={20}
                                    source={UserIcon}
                                />
                            </View>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </View>
    );
}
