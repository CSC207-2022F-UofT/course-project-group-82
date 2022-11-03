import { Colors, TextField } from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function PasswordInput(props: {
  password: string;
  passwordChange: (text: string) => void;
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
      placeholder={"Password"}
      fieldStyle={styles.withUnderline}
      onChangeText={props.passwordChange}
      value={props.password}
      secureTextEntry={true}
      enableErrors
      validate={["required", "password", (value: string) => value.length > 6]}
      validationMessage={[
        "Field is required",
        "Username is invalid",
        "Password is too short",
      ]}
      maxLength={16}
      migrate
    />
  );
}
