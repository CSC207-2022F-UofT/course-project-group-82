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
                        <Stack.Group>
                            <Stack.Screen
                                name="Home"
                                component={LoginPage}
                                options={{ headerShown: false }}
                            />
                            <Stack.Screen
                                name={"Register"}
                                component={RegisterPage}
                                options={{ headerShown: false }}
                            />
                        </Stack.Group>
                    ) : (
                        <Stack.Group>
                            <Stack.Screen
                                name={"Dashboard"}
                                component={DashboardPage}
                                options={{ headerShown: false }}
                            />
                            <Stack.Screen
                                name={"Notifications"}
                                component={NotificationsPage}
                                options={{ headerShown: false }}
                            />
                            <Stack.Screen
                                name={"FindRestaurants"}
                                component={FindRestaurantsPage}
                                options={{ headerShown: false }}
                            />
                            <Stack.Screen
                                name={"MakePost"}
                                component={RecommendRestaurantPage}
                                options={{ headerShown: false }}
                            />
                            <Stack.Screen
                                name={"UserProfile"}
                                component={UserProfilePage}
                                options={{ headerShown: false }}
                            />
                        </Stack.Group>
                    )}
                </Stack.Navigator>
            </NavigationContainer>
        </UserContext.Provider>
    );
}
