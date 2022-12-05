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
import { FriendListItem } from "./components/FriendListItem/FriendListItem";

export function NotificationsPageView(props: {
    navigation: any;
    notifications: OuiNotification[];
    closeModal: () => void;
    openModal: () => void;
    modalVisible: boolean;
    loading: boolean;
    users: Array<UserPreviewInterface>;
    friends: Array<UserPreviewInterface>;
    searchUserText: string;
    updateSearchUserText: (text: string) => void;
    searchForUsersByUsername: () => void;
    sendFriendRequest: (recipientId: string, recipientUsername: string) => void;
    refreshNotifications: () => void;
    refreshFriendsList: () => void;
    refreshingFriends: boolean;
    refreshing: boolean;
    handleFriendRequest: (
        notification: OuiNotification,
        accept: boolean
    ) => void;
    handleRemoveFriend: (friendId: string) => void;
}) {
    function masterRefreshControl() {
        return (
            <RefreshControl
                refreshing={props.refreshingFriends || props.refreshing}
                onRefresh={() => {
                    props.refreshFriendsList();
                    props.refreshNotifications();
                }}
            />
        );
    }

    function friendRequestRefreshControl() {
        return (
            <RefreshControl
                refreshing={props.refreshing}
                onRefresh={props.refreshNotifications}
            />
        );
    }

    function friendsListRefreshControl() {
        return (
            <RefreshControl
                refreshing={props.refreshingFriends}
                onRefresh={props.refreshFriendsList}
            />
        );
    }

    return (
        <SafeAreaView className={"h-full w-full"}>
            <View className={"flex flex-col flex-1 bg-[#fff]"}>
                <Navbar navigation={props.navigation} />

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

                <ScrollView refreshControl={masterRefreshControl()}>
                    {/* Title */}
                    <View className={"flex flex-col py-5"}>
                        <Text className={"text-xl px-5"}>Your friends</Text>
                        <View className={"w-1/2 bg-[#ffb700] h-1 mx-4"} />
                    </View>

                    <View className={"flex flex-col px-5 pb-5"}>
                        {props.friends.map((friend: UserPreviewInterface) => {
                            return (
                                <FriendListItem
                                    friend={friend}
                                    handleRemoveFriend={
                                        props.handleRemoveFriend
                                    }
                                    key={friend.id}
                                />
                            );
                        })}
                    </View>

                    {/* Title */}
                    <View className={"flex flex-col py-5"}>
                        <Text className={"text-xl px-5"}>Requests</Text>
                        <View className={"w-1/2 bg-[#ffb700] h-1 mx-4"} />
                    </View>

                    <View className={"flex flex-col px-5 pb-5"}>
                        {props.notifications.map((notification) => {
                            return (
                                <FriendRequestNotification
                                    notification={notification}
                                    handleFriendRequest={
                                        props.handleFriendRequest
                                    }
                                />
                            );
                        })}
                    </View>
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
