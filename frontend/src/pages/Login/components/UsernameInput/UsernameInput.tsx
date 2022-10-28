import { Colors, TextField } from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function UsernameInput(props: {
  username: string;
  usernameChange: (text: string) => void;
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
      placeholder={"Username"}
      fieldStyle={styles.withUnderline}
      floatingPlaceholder
      onChangeText={props.usernameChange}
      value={props.username}
      enableErrors
      validate={["required", "username", (value: string) => value.length > 6]}
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
