import { Colors, TextField } from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function PasswordInput(props: {
  password: string;
  passwordChange: (text: string) => void;
}) {
  const styles = StyleSheet.create({
    withUnderline: {
      borderBottomWidth: 1,
      borderColor: Colors.$outlineDisabledHeavy,
      paddingBottom: 4,
    },
  });

  return (
    <TextField
      placeholder={"Password"}
      fieldStyle={styles.withUnderline}
      floatingPlaceholder
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
