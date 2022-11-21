import { SafeAreaView } from "react-native-safe-area-context";
import { View, Text, ScrollView, RefreshControl } from "react-native";
import Navbar from "./components/Navbar";
import { Button } from "react-native-ui-lib";
import { OuiRecommendations } from "./types/OuiRecommendations";
import Recommendation from "./components/Recommendation";

export function DashboardPageView(props: {
    userID: string | null;
    doLogout: () => void;
    showNotificationsPage: () => void;
    recommendations: OuiRecommendations[];
    findRestaurants: () => void;
    makePosts: () => void;
    onRefresh: () => void;
    refreshing: boolean;
}) {
    function RecommendationMapping() {
        if (!props.recommendations || props.recommendations.length === 0) {
            return (
                <View className={"flex flex-row justify-center items-center"}>
                    <Text className={"text-lg text-gray-500"}>
                        You have no recommendations yet!
                    </Text>
                </View>
            );
        } else {
            return (
                <>
                    {props.recommendations.map((recommendation, index) => (
                        <Recommendation
                            recommendation={recommendation}
                            key={recommendation.id}
                        />
                    ))}
                </>
            );
        }
    }

    function dashboardRefreshControl() {
        return (
            <RefreshControl
                refreshing={props.refreshing}
                onRefresh={props.onRefresh}
            />
        );
    }

    return (
        <SafeAreaView className={"bg-[#ffffff]"}>
            <View className={"h-full w-full flex flex-col"}>
                <Navbar
                    showNotificationsPage={props.showNotificationsPage}
                    doLogout={props.doLogout}
                />

                {/* All Recommendations */}
                <View className={"w-full flex flex-1 flex-col p-3 mt-5"}>
                    <ScrollView refreshControl={dashboardRefreshControl()}>
                        <RecommendationMapping />
                    </ScrollView>
                </View>

                <View className={"flex flex-col p-2"}>
                    <View className={"flex flex-row justify-center gap-x-3"}>
                        <Button
                            onPress={props.findRestaurants}
                            iconOnRight={false}
                            borderRadius={10}
                            label={"Find a Restaurant"}
                            size={Button.sizes.large}
                            backgroundColor={"#FFB700"}
                        />
                        <Button
                            onPress={props.makePosts}
                            iconOnRight={false}
                            borderRadius={10}
                            label={"Make a post"}
                            size={Button.sizes.large}
                            backgroundColor={"#FFB700"}
                        />
                    </View>
                </View>
            </View>
        </SafeAreaView>
    );
}
