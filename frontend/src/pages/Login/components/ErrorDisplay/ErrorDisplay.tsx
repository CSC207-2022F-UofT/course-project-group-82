import { View } from "react-native";
import { Text } from "react-native-ui-lib";

export function ErrorDisplay(props: { error: string }) {
  return (
    <View>
      <Text text30 color={"red"}>
        {props.error}
      </Text>
    </View>
  );
}
