import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NavigationContainer } from "@react-navigation/native";
import LoginPage from "./src/pages/Login";
import { Logs } from "expo";
import { useEffect, useState } from "react";
import { UserContext } from "./src/context/UserContext";

import DashboardPage from "./src/pages/Dashboard";
import RegisterPage from "./src/pages/Register";
import NotificationsPage from "./src/pages/Notifications";
import FindRestaurantsPage from "./src/pages/FindRestaurants";
import RecommendRestaurantPage from "./src/pages/RecommendRestaurant";
import UserProfilePage from "./src/pages/UserProfile";
import { LogBox } from "react-native";

export default function App() {
    LogBox.ignoreAllLogs(); // REMOVE THIS LATER

    const Stack = createNativeStackNavigator();
    const [userID, setUserID] = useState<string | null>("");
    const userValue = { userID, setUserID };

    useEffect(() => {
        Logs.enableExpoCliLogging();
    });

    return (
        <UserContext.Provider value={userValue}>
            <NavigationContainer>
                <Stack.Navigator
                    screenOptions={{
                        statusBarColor: "#ffb700",
                        contentStyle: { backgroundColor: "#ffb700" },
                        headerShown: false,
                    }}
                >
                    {!userID ? (
                        <Stack.Group>
                            <Stack.Screen name="Home" component={LoginPage} />
                            <Stack.Screen
                                name={"Register"}
                                component={RegisterPage}
                            />
                        </Stack.Group>
                    ) : (
                        <Stack.Group>
                            <Stack.Screen
                                name={"Dashboard"}
                                component={DashboardPage}
                            />
                            <Stack.Screen
                                name={"Notifications"}
                                component={NotificationsPage}
                            />
                            <Stack.Screen
                                name={"FindRestaurants"}
                                component={FindRestaurantsPage}
                            />
                            <Stack.Screen
                                name={"MakePost"}
                                component={RecommendRestaurantPage}
                            />
                            <Stack.Screen
                                name={"UserProfile"}
                                component={UserProfilePage}
                            />
                        </Stack.Group>
                    )}
                </Stack.Navigator>
            </NavigationContainer>
        </UserContext.Provider>
    );
}
