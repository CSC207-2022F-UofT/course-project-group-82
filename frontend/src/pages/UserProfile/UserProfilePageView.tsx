import {
    ImageBackground,
    SafeAreaView,
    StyleSheet,
    Text,
    TouchableOpacity,
    View,
} from "react-native";
import Navbar from "../../components/Navbar";
import React from "react";
import {
    Button as RNButton,
    Button,
    KeyboardAwareScrollView,
    TextField,
} from "react-native-ui-lib";

export function UserProfilePageView(props: {
    navigation: any;
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
    profilePictureLink: string;
    pickImage: () => void;
    updateUserProfile: () => void;
    doLogout: () => void;
}) {
    const styles = StyleSheet.create({
        withUnderline: {
            backgroundColor: "#DEE3E7",
            borderRadius: 10,
            padding: 10,
        },
    });

    return (
        <SafeAreaView className={"h-full w-full"}>
            <View className={"flex flex-col flex-1 bg-[#fff]"}>
                <Navbar navigation={props.navigation} />

                <KeyboardAwareScrollView>
                    <View className={"flex flex-col flex-1 p-5"}>
                        <View className={"flex flex-col pb-5"}>
                            <Text className={"text-xl px-5"}>
                                Edit the profile
                            </Text>
                            <View className={"w-1/2 bg-[#ffb700] h-1 mx-4"} />
                        </View>

                        <View className={"flex flex-col"}>
                            <View
                                className={"flex flex-row justify-center my-3"}
                            >
                                <TouchableOpacity onPress={props.pickImage}>
                                    <View
                                        className={
                                            "h-24 w-24 rounded-full shadow-lg bg-red-100"
                                        }
                                    >
                                        <ImageBackground
                                            source={{
                                                uri: props.profilePictureLink,
                                            }}
                                            style={{
                                                width: "100%",
                                                height: "100%",
                                            }}
                                            imageStyle={{ borderRadius: 999 }}
                                        />
                                    </View>
                                </TouchableOpacity>
                            </View>

                            <TextField
                                placeholder={"Username"}
                                value={props.username}
                                onChangeText={props.updateUsername}
                                fieldStyle={styles.withUnderline}
                                enableErrors
                                maxLength={16}
                                migrate
                            />

                            <View
                                className={
                                    "flex flex-row justify-between gap-x-2"
                                }
                            >
                                <View className={"flex flex-1"}>
                                    <TextField
                                        placeholder={"First Name"}
                                        value={props.firstName}
                                        onChangeText={props.updateFirstName}
                                        fieldStyle={styles.withUnderline}
                                        enableErrors
                                        maxLength={16}
                                        migrate
                                    />
                                </View>
                                <View className={"flex flex-1"}>
                                    <TextField
                                        placeholder={"Last Name"}
                                        value={props.lastName}
                                        onChangeText={props.updateLastName}
                                        fieldStyle={styles.withUnderline}
                                        enableErrors
                                        maxLength={16}
                                        migrate
                                    />
                                </View>
                            </View>

                            <TextField
                                placeholder={"Email"}
                                value={props.email}
                                onChangeText={props.updateEmail}
                                fieldStyle={styles.withUnderline}
                                enableErrors
                                maxLength={32}
                                migrate
                            />

                            <View className={"flex flex-col"}>
                                <View
                                    className={"flex flex-row justify-center"}
                                >
                                    <RNButton
                                        onPress={props.doLogout}
                                        borderRadius={10}
                                        label={"Logout"}
                                        backgroundColor={"#FF5A5A"}
                                    />
                                </View>
                            </View>
                        </View>
                    </View>
                </KeyboardAwareScrollView>
            </View>

            {/*Update button*/}
            <View className={"flex flex-col"}>
                <View className={"flex flex-row justify-center"}>
                    <RNButton
                        onPress={props.updateUserProfile}
                        borderRadius={10}
                        label={"Update"}
                        backgroundColor={"#FFB700"}
                    />
                </View>
            </View>
        </SafeAreaView>
    );
}
