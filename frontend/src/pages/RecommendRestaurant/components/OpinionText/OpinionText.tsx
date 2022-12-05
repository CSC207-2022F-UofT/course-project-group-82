import { TextField } from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function OpinionText(props: {
    opinionText: string;
    opinionTextChange: (text: string) => void;
}) {
    const styles = StyleSheet.create({
        withUnderline: {
            backgroundColor: "#DEE3E7",
            borderRadius: 10,
            padding: 10,
        },
    });

    return (
        <TextField
            placeholder={"Opinion Tags"}
            fieldStyle={styles.withUnderline}
            onChangeText={props.opinionTextChange}
            value={props.opinionText}
            enableErrors
            maxLength={30}
            migrate
        />
    );
}
