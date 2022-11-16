import React from "react";
import { View, SafeAreaView, StyleSheet, TextInput, Text} from "react-native";

export const UserProfilePage = () => {
    const [text, onChangeFirstName] = React.useState<string>();
    const [text2, onChangeLastName] = React.useState<string>();

    return (
        <SafeAreaView>
            <View style={{display: "flex", flexDirection: "row", alignItems: "center"}}>
                <Text>First Name</Text>
                <TextInput
                    placeholder = {"First Name"}
                    style={styles.input}
                    onChangeText={onChangeFirstName}
                    value={text}
                />
            </View>
            <View style={{display: "flex", flexDirection: "row", alignItems: "center"}}>
                <Text>Last Name</Text>
                <TextInput
                    placeholder = {"Last Name"}
                    style={styles.input}
                    onChangeText={onChangeLastName}
                    value={text2}
                />
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    input: {
        height: 40,
        margin: 12,
        borderWidth: 1,
        padding: 10,
    },
});