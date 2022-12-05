import { UserPreviewInterface } from "../../../../services/User/UserInterface";
import { ImageBackground, Text, TouchableOpacity, View } from "react-native";
import React from "react";

export function FriendListItem(props: {
    friend: UserPreviewInterface;
    handleRemoveFriend: (friendId: string) => void;
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
                    "flex flex-col h-20 border border-[#dee3e7] rounded-xl px-5 my-3 justify-center"
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
                                        props.friend.profilePictureLink ||
                                        "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
                                }}
                            />
                        </View>
                    </TouchableOpacity>

                    {/* User that sent the request name */}
                    <View className={"flex flex-1 flex-row justify-between"}>
                        <View className={"flex flex-col gap-y-[-3]"}>
                            <Text className={"text-lg font-semibold"}>
                                {props.friend.firstName +
                                    " " +
                                    props.friend.lastName}
                            </Text>
                            <Text className={"text-xs text-gray-400"}>
                                @{props.friend.username}
                            </Text>
                        </View>
                    </View>

                    <View className={"flex flex-row"}>
                        {/* Remove Friend */}
                        <TouchableOpacity
                            onPress={() => {
                                props.handleRemoveFriend(props.friend.id);
                            }}
                            className={
                                "bg-[#FF5A5A] h-10 flex flex-col items-center justify-center rounded-xl"
                            }
                        >
                            <Text className={"text-white text-center p-1"}>
                                Remove
                            </Text>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </View>
    );
}
