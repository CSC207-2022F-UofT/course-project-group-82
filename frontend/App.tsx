import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NavigationContainer } from "@react-navigation/native";
import LoginPage from "./src/pages/Login";
import { Logs } from "expo";
import { useEffect, useState } from "react";
import { UserContext } from "./src/context/UserContext";
import DashboardPage from "./src/pages/Dashboard";

export default function App() {
  const Stack = createNativeStackNavigator();
  const [userID, setUserID] = useState<string | null>("");
  const userValue = { userID, setUserID };

  useEffect(() => {
    Logs.enableExpoCliLogging();
  });

  return (
    <UserContext.Provider value={userValue}>
      <NavigationContainer>
        <Stack.Navigator>
          {!userID ? (
            <Stack.Screen
              name="Home"
              component={LoginPage}
              options={{ headerShown: false }}
            />
          ) : (
            <Stack.Screen
              name={"Dashboard"}
              component={DashboardPage}
              options={{ headerShown: false }}
            />
          )}
        </Stack.Navigator>
      </NavigationContainer>
    </UserContext.Provider>
  );
}
