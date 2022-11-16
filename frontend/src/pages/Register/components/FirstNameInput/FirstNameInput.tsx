import { Colors, TextField } from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function FirstNameInput(props: {
    firstName: string;
    firstNameChange: (text: string) => void;
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
            containerStyle={{ flex: 1, marginRight: 20 }}
            placeholder={"First Name"}
            fieldStyle={styles.withUnderline}
            onChangeText={props.firstNameChange}
            value={props.firstName}
            enableErrors
            validate={[
                "required",
                "First Name",
                (value: string) => value.length > 6,
            ]}
            validationMessage={[
                "Field is required",
                "First Name is invalid",
                "Password is too short",
            ]}
            maxLength={16}
            migrate
        />
    );
}
