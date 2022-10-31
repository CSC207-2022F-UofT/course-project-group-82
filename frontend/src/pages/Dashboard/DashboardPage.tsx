import { Text, View } from "react-native";
import * as SecureStore from "expo-secure-store";
import { useContext, useEffect, useState } from "react";
import { SafeAreaView } from "react-native-safe-area-context";
import { Button } from "react-native-ui-lib";
import { UserContext } from "../../context/UserContext";
import { setItemAsync } from "expo-secure-store";

export function DashboardPage() {
  const { userID, setUserID } = useContext(UserContext);

  async function doLogout() {
    setUserID(null);
    await setItemAsync("userToken", "");
  }

  return (
    <SafeAreaView>
      <View className={"h-full justify-center items-center"}>
        <Text>User logged in with token: {userID}</Text>
        <Button
          onPress={doLogout}
          label={"Log out"}
          size={Button.sizes.medium}
          backgroundColor={"#FFB700"}
        />
      </View>
    </SafeAreaView>
  );
}
