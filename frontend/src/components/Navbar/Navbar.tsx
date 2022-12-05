import {
    Image,
    ImageBackground,
    StyleSheet,
    TouchableOpacity,
    View,
} from "react-native";
import { useCallback, useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { setItemAsync } from "expo-secure-store";

import SimpleIcon from "./assets/SimpleIcon.png";
import OuieatIcon from "./assets/Ouieat.png";
import EntIcon from "react-native-vector-icons/Entypo";
import IonIcon from "react-native-vector-icons/Ionicons";

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

    function showDashboardPage() {
        props.navigation.navigate("Dashboard");
    }

    function showFinderPage() {
        props.navigation.navigate("FindRestaurants");
    }

    function showUserProfilePage() {
        props.navigation.navigate("UserProfile");
    }

    function showNotificationsPage() {
        props.navigation.navigate("Notifications");
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
        withShadow: {
            shadowColor: "#000",
            shadowOpacity: 0.2,
            shadowRadius: 3,
            shadowOffset: {
                height: 5,
                width: 0,
            },
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
                                className={"h-12 w-12"}
                            />
                            <Image
                                source={OuieatIcon}
                                resizeMode={"contain"}
                                className={"h-auto w-24"}
                            />
                        </View>
                    </TouchableOpacity>
                    <View
                        className={
                            "flex flex-row justify-end items-center gap-x-2"
                        }
                    >
                        <View className={"flex flex-row items-center gap-x-2"}>
                            <TouchableOpacity
                                style={styles.withShadow}
                                onPress={showNotificationsPage}
                                className={"rounded-full bg-[#FFDC25] p-1"}
                            >
                                <View className={"flex flex-row items-center"}>
                                    <View className={"shadow-lg rounded-full"}>
                                        <IonIcon
                                            name={"notifications-outline"}
                                            size={24}
                                        />
                                    </View>
                                </View>
                            </TouchableOpacity>
                        </View>

                        <TouchableOpacity
                            style={styles.withShadow}
                            onPress={showUserProfilePage}
                        >
                            <View
                                className={
                                    "h-8 w-8 rounded-full overflow-hidden"
                                }
                            >
                                <ImageBackground
                                    className={"w-full h-full"}
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
