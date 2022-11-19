import {
    ImageBackground,
    SafeAreaView,
    ScrollView,
    Text,
    View,
} from "react-native";
import BackgroundImage from "./assets/NotificationsBackgroundImage.png";
import classNames from "classnames";
import { OuiNotification } from "./types/OuiNotification";

export function NotificationsPageView(props: {
    navigation: any;
    notifications: OuiNotification[];
}) {
    const viewClassnames = classNames("w-full m-auto h-full self-center");

    const NotificationMapping = () => {
        return (
            <>
                {props.notifications.map((notification) => {
                    return (
                        <View
                            key={notification.id}
                            className={"flex flex-col justify-center px-5"}
                        >
                            <View
                                className={
                                    "bg-[#FFB700] opacity-[0.3] h-0.5 w-full"
                                }
                            />
                            <Text className={"text-xl font-bold"}>
                                {notification.title}
                            </Text>
                            <Text className={"text-md"}>
                                {notification.body}
                            </Text>
                            <Text className={"text-sm text-gray"}>
                                {notification.timestamp}
                            </Text>
                            <View
                                className={
                                    "bg-[#FFB700] opacity-[0.3] h-0.5 w-full"
                                }
                            />
                        </View>
                    );
                })}
            </>
        );
    };

    const NotificationOutput = () => {
        if (props.notifications.length > 0) return <NotificationMapping />;
        else
            return (
                <Text className={"text-xl font-bold text-left px-5"}>
                    No notifications :(
                </Text>
            );
    };

    return (
        <ImageBackground source={BackgroundImage}>
            <SafeAreaView>
                <View className={viewClassnames}>
                    {/*Notifications Header */}
                    <View className={"flex flex-row justify-center my-5"}>
                        <Text className={"text-3xl font-bold"}>
                            Notifications
                        </Text>
                    </View>

                    {/*Notifications Scroll List*/}
                    <ScrollView
                        className={"flex flex-row"}
                        contentContainerStyle={{
                            flexGrow: 1,
                        }}
                    >
                        <NotificationOutput />
                    </ScrollView>
                </View>
            </SafeAreaView>
        </ImageBackground>
    );
}
