import { View } from "react-native";
import { Text, Toast } from "react-native-ui-lib";

export function ErrorDisplay(props: { error: string; errorVisible: boolean }) {
  return (
    <View>
      <Toast
        visible={props.errorVisible}
        position={"bottom"}
        autoDismiss={5000}
        message={props.error}
      />
    </View>
  );
}
