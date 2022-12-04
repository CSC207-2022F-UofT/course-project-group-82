import { UserPreviewInterface } from "../../../../services/User/UserInterface";
import {
    ImageBackground,
    Modal,
    SafeAreaView,
    ScrollView,
    Text,
    TouchableOpacity,
    View,
} from "react-native";
import { BlurView } from "expo-blur";
import { Button as RNButton, TextField } from "react-native-ui-lib";
import React from "react";

export function UserByUsernameFinder(props: {
    modalVisible: boolean;
    loading: boolean;
    closeModal: () => void;
    users: Array<UserPreviewInterface>;
    searchUserText: string;
    updateSearchUserText: (text: string) => void;
    searchForUsersByUsername: () => void;
}) {
    const withShadow = {
        shadowColor: "#000",
        shadowOpacity: 0.3,
        shadowRadius: 8,
        shadowOffset: {
            height: 0,
            width: 0,
        },
    };

    const withAvatarShadow = {
        shadowColor: "#000",
        shadowOpacity: 0.3,
        shadowRadius: 3,
        shadowOffset: {
            height: 5,
            width: 0,
        },
    };

    const withUnderline = {
        backgroundColor: "#DEE3E7",
        borderRadius: 10,
        padding: 10,
    };

    function SingleUserPreview(props: { user: UserPreviewInterface }) {
        return (
            <View
                className={
                    "border border-[#dee3e7] h-16 rounded-xl flex flex-col justify-center px-5 my-3"
                }
            >
                <View className={"flex flex-row items-center gap-x-4"}>
                    {/* User that sent the request Avatar */}
                    <TouchableOpacity style={withAvatarShadow}>
                        <View
                            className={"h-8 w-8 rounded-full overflow-hidden"}
                        >
                            <ImageBackground
                                className={"w-full h-full"}
                                source={{
                                    uri:
                                        props.user.profilePictureLink ||
                                        "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
                                }}
                            />
                        </View>
                    </TouchableOpacity>

                    {/* User that sent the request name */}
                    <View className={"flex flex-1 flex-row justify-between"}>
                        <View className={"flex flex-col gap-y-[-3]"}>
                            <Text className={"font-semibold"}>
                                @{props.user.username}
                            </Text>
                            <Text className={"text-xs text-gray-400"}>
                                {props.user.firstName +
                                    " " +
                                    props.user.lastName}
                            </Text>
                        </View>
                    </View>

                    <View className={"flex flex-row"}>
                        <TouchableOpacity
                            className={"bg-[#ffb700] p-1.5 rounded-md"}
                        >
                            <Text
                                className={"text-white text-sm font-semibold"}
                            >
                                Send
                            </Text>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        );
    }

    return (
        <Modal
            animationType={"slide"}
            transparent={true}
            visible={props.modalVisible}
        >
            <BlurView className={"h-full w-full"} intensity={20}>
                <SafeAreaView
                    style={{
                        position: "absolute",
                        top: 0,
                        left: 0,
                        right: 0,
                        height: "100%",
                    }}
                >
                    <View
                        className={
                            "flex flex-1 flex-col justify-center items-center"
                        }
                    >
                        <View
                            style={withShadow}
                            className={
                                "bg-[#fff] h-3/4 w-3/4 rounded-lg border border-[#dee3e7]"
                            }
                        >
                            {/* Header */}
                            <View
                                className={
                                    "flex flex-col items-center px-5 pt-3"
                                }
                            >
                                <Text className={"text-xl"}>Add a friend</Text>
                                <View className={"bg-[#ffb700] h-1 w-1/2"} />
                            </View>

                            {/* Search fields */}
                            <View className={"flex flex-row p-3 gap-x-2"}>
                                <View className={"flex flex-col flex-1"}>
                                    <TextField
                                        placeholder={"Enter a username"}
                                        value={props.searchUserText}
                                        onChangeText={
                                            props.updateSearchUserText
                                        }
                                        fieldStyle={withUnderline}
                                        maxLength={16}
                                        migrate
                                    />
                                </View>
                                <RNButton
                                    onPress={props.searchForUsersByUsername}
                                    label={"Search"}
                                    borderRadius={10}
                                    backgroundColor={"#FFB700"}
                                    size={"small"}
                                />
                            </View>

                            <ScrollView className={"flex flex-col p-5 gap-y-3"}>
                                {props.users.map((user) => (
                                    <SingleUserPreview user={user} />
                                ))}
                            </ScrollView>
                            {/* Add friend button button*/}
                            <View className={"flex flex-col mb-1"}>
                                <View
                                    className={
                                        "flex flex-row justify-center items-center"
                                    }
                                >
                                    <RNButton
                                        onPress={props.closeModal}
                                        borderRadius={10}
                                        label={" Close"}
                                        backgroundColor={"#FFB700"}
                                    />
                                </View>
                            </View>
                        </View>
                    </View>
                </SafeAreaView>
            </BlurView>
        </Modal>
    );
}
