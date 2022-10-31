import { View } from "react-native";
import { Text, Toast } from "react-native-ui-lib";
import { Dispatch, SetStateAction } from "react";

export function ErrorDisplay(props: {
  error: string;
  errorVisible: boolean;
  setErrorVisible: Dispatch<SetStateAction<boolean>>;
}) {
  return (
    <View>
      <Toast
        visible={props.errorVisible}
        position={"top"}
        message={props.error}
        centerMessage
        backgroundColor={"red"}
      />
    </View>
  );
}
