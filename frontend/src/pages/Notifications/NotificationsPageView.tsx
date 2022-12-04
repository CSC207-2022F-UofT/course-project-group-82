import {
    ImageBackground,
    RefreshControl,
    SafeAreaView,
    ScrollView,
    Text,
    View,
} from "react-native";
import { OuiNotification } from "./types/OuiNotification";
import Navbar from "../../components/Navbar";
import React from "react";
import { Button as RNButton } from "react-native-ui-lib";
import EntypoIcon from "react-native-vector-icons/Entypo";
import { UserPreviewInterface } from "../../services/User/UserInterface";
import UserByUsernameFinder from "./components/UserByUsernameFinder";
import FriendRequestNotification from "./components/FriendRequestNotification";

export function NotificationsPageView(props: {
    navigation: any;
    notifications: OuiNotification[];
    closeModal: () => void;
    openModal: () => void;
    modalVisible: boolean;
    loading: boolean;
    users: Array<UserPreviewInterface>;
    searchUserText: string;
    updateSearchUserText: (text: string) => void;
    searchForUsersByUsername: () => void;
    sendFriendRequest: (recipientId: string, recipientUsername: string) => void;
    refreshNotifications: () => void;
    refreshing: boolean;
}) {
    function friendRequestRefreshControl() {
        return (
            <RefreshControl
                refreshing={props.refreshing}
                onRefresh={props.refreshNotifications}
            />
        );
    }

    return (
        <SafeAreaView className={"h-full w-full"}>
            <View className={"flex flex-col flex-1 bg-[#fff]"}>
                <Navbar navigation={props.navigation} />

                {/* Title */}
                <View className={"flex flex-col py-5"}>
                    <Text className={"text-xl px-5"}>Friend requests</Text>
                    <View className={"w-1/2 bg-[#ffb700] h-1 mx-4"} />
                </View>

                {/* User finder modal */}
                <UserByUsernameFinder
                    modalVisible={props.modalVisible}
                    loading={props.loading}
                    closeModal={props.closeModal}
                    users={props.users}
                    searchUserText={props.searchUserText}
                    updateSearchUserText={props.updateSearchUserText}
                    searchForUsersByUsername={props.searchForUsersByUsername}
                    sendFriendRequest={props.sendFriendRequest}
                />

                <ScrollView
                    className={"flex flex-col p-5"}
                    refreshControl={friendRequestRefreshControl()}
                >
                    {props.notifications.map((notification) => {
                        return (
                            <FriendRequestNotification
                                notification={notification}
                            />
                        );
                    })}
                </ScrollView>
            </View>
            {/* Add friend button button*/}
            <View className={"flex flex-col"}>
                <View className={"flex flex-row justify-center items-center"}>
                    <RNButton
                        onPress={props.openModal}
                        borderRadius={10}
                        label={" Add a friend"}
                        backgroundColor={"#FFB700"}
                    >
                        <EntypoIcon name={"plus"} size={24} color={"#fff"} />
                    </RNButton>
                </View>
            </View>
        </SafeAreaView>
    );
}
