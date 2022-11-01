import { StyleSheet } from "react-native";
import { TextField } from "react-native-ui-lib";

export function EmailInput(props: {
  email: string;
  emailChange: (text: string) => void;
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
      placeholder={"Email"}
      fieldStyle={styles.withUnderline}
      onChangeText={props.emailChange}
      value={props.email}
      enableErrors
      validate={["required", "email", (value: string) => value.length > 6]}
      validationMessage={[
        "Field is required",
        "Email is invalid",
        "Password is too short",
      ]}
      maxLength={16}
      migrate
    />
  );
}
