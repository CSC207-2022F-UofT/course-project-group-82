import { StatusBar } from "expo-status-bar";
import { Text, View } from "react-native";
import classNames from "classnames";

export default function App() {
  const viewClassnames = classNames(
    "flex-1 items-center justify-center bg-white"
  );

  return (
    <View className={viewClassnames}>
      <Text>Open up App.tsx to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
}
