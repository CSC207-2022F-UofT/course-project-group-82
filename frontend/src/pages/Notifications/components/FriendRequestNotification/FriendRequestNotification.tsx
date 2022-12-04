import { ImageBackground, Text, TouchableOpacity, View } from "react-native";
import EntypoIcon from "react-native-vector-icons/Entypo";
import React from "react";
import { OuiNotification } from "../../types/OuiNotification";

export function FriendRequestNotification(props: {
    notification: OuiNotification;
}) {
    const withShadow = {
        shadowColor: "#000",
        shadowOpacity: 0.2,
        shadowRadius: 3,
        shadowOffset: {
            height: 5,
            width: 0,
        },
    };

    return (
        <View>
            {/* A single request */}
            <View
                className={
                    "flex flex-col h-20 border border-[#dee3e7] rounded-xl px-5 justify-center"
                }
            >
                <View className={"flex flex-row items-center gap-x-4"}>
                    {/* User that sent the request Avatar */}
                    <TouchableOpacity style={withShadow}>
                        <View
                            className={"h-8 w-8 rounded-full overflow-hidden"}
                        >
                            <ImageBackground
                                className={"w-full h-full"}
                                source={{
                                    uri:
                                        props.notification
                                            .senderProfilePictureLink ||
                                        "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
                                }}
                            />
                        </View>
                    </TouchableOpacity>

                    {/* User that sent the request name */}
                    <View className={"flex flex-1 flex-row justify-between"}>
                        <View className={"flex flex-col gap-y-[-3]"}>
                            <Text className={"text-lg font-semibold"}>
                                {props.notification.senderName}
                            </Text>
                            <Text className={"text-xs text-gray-400"}>
                                Sent you a friend request
                            </Text>
                        </View>
                    </View>

                    {/* Toggle Select for Accept/Decline */}
                    <View className={"flex flex-row"}>
                        {/* Accept Request */}
                        <TouchableOpacity
                            className={
                                "bg-[#2EC03DCC] h-10 w-12 flex flex-col items-center justify-center rounded-l-xl"
                            }
                        >
                            <EntypoIcon
                                name={"check"}
                                size={20}
                                color={"#fff"}
                            />
                        </TouchableOpacity>
                        {/* Decline Request */}
                        <TouchableOpacity
                            className={
                                "bg-[#FF5A5ACC] h-10 w-12 flex flex-col items-center justify-center rounded-r-xl"
                            }
                        >
                            <EntypoIcon
                                name={"cross"}
                                size={24}
                                color={"#fff"}
                            />
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </View>
    );
}
