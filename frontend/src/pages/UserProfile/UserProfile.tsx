import React from "react";
import {
    View,
    SafeAreaView,
    StyleSheet,
    TextInput,
    Text,
    ImageBackground,
    Button,
} from "react-native";
import BackgroundImage from "./assets/userprofilebackground.png";
import { AntDesign } from "@expo/vector-icons";
import UploadImage from "./UploadImage";

export const UserProfilePage = () => {
    const [username, onChangeUsername] = React.useState<string>();
    const [text, onChangeFirstName] = React.useState<string>();
    const [text2, onChangeLastName] = React.useState<string>();
    const [text3, onChangeEmail] = React.useState<string>();
    const [text4, onChangePassword] = React.useState<string>();

    return (
        <ImageBackground source={BackgroundImage} style={styles.container}>
            <SafeAreaView
                style={{
                    flex: 0.8,
                    justifyContent: "center",
                    alignItems: "center",
                }}
            >
                <Text style={{ fontSize: 25, fontWeight: "bold" }}>
                    Edit Profile
                    {"\n"}
                </Text>
                <UploadImage />
                <Text>{"\n"}</Text>
                <View
                    style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                    }}
                >
                    <AntDesign name="idcard" size={24} color="black" />
                    <TextInput
                        placeholder={"Username"}
                        style={styles.input}
                        onChangeText={onChangeUsername}
                        value={username}
                    />
                </View>
                <View
                    style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                    }}
                >
                    <AntDesign name="user" size={24} color="black" />
                    <TextInput
                        placeholder={"First Name"}
                        style={styles.input}
                        onChangeText={onChangeFirstName}
                        value={text}
                    />
                </View>
                <View
                    style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                    }}
                >
                    <AntDesign name="user" size={24} color="black" />
                    <TextInput
                        placeholder={"Last Name"}
                        style={styles.input}
                        onChangeText={onChangeLastName}
                        value={text2}
                    />
                </View>
                <View
                    style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                    }}
                >
                    <AntDesign name="mail" size={24} color="black" />
                    <TextInput
                        placeholder={"Email"}
                        style={styles.input}
                        onChangeText={onChangeEmail}
                        value={text3}
                    />
                </View>
                <View
                    style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                    }}
                >
                    <AntDesign name="lock1" size={24} color="black" />
                    <TextInput
                        placeholder={"Password"}
                        style={styles.input}
                        onChangeText={onChangePassword}
                        value={text4}
                    />
                </View>
            </SafeAreaView>
        </ImageBackground>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: "100%",
        height: "100%",
    },
    input: {
        height: 40,
        margin: 12,
        width: "50%",
        borderWidth: 1,
        borderRadius: 10,
        padding: 10,
    },
});
