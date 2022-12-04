import { SafeAreaView } from "react-native-safe-area-context";
import { View, Text, ScrollView, RefreshControl } from "react-native";
import { Button } from "react-native-ui-lib";
import Recommendation from "./components/Recommendation";
import Navbar from "../../components/Navbar";
import IonIcon from "react-native-vector-icons/Ionicons";
import { OuiRecommendations } from "../../data_types";
import React from "react";

export function DashboardPageView(props: {
    navigation: any;
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
        <SafeAreaView>
            <View className={"bg-[#ffffff] h-full w-full flex flex-col"}>
                <Navbar navigation={props.navigation} />

                {/* All Recommendations */}
                <View className={"w-full flex flex-1 flex-col"}>
                    {/* Title */}
                    <View className={"flex flex-col py-5"}>
                        <Text className={"text-xl px-5"}>Recent</Text>
                        <View className={"w-1/2 bg-[#ffb700] h-1 mx-4"} />
                    </View>

                    <ScrollView
                        refreshControl={dashboardRefreshControl()}
                        contentContainerStyle={{
                            paddingTop: 15,
                            paddingHorizontal: 10,
                        }}
                    >
                        <RecommendationMapping />
                    </ScrollView>
                </View>

                <View className={"flex flex-col"}>
                    <View className={"flex flex-row justify-around"}>
                        <View className={"flex flex-1"}>
                            <Button
                                onPress={props.makePosts}
                                iconOnRight={false}
                                fullWidth
                                label={" Make a post "}
                                size={Button.sizes.large}
                                backgroundColor={"#FFB700"}
                            >
                                <IonIcon
                                    name={"create-outline"}
                                    color={"white"}
                                    size={20}
                                />
                            </Button>
                        </View>
                    </View>
                </View>
            </View>
        </SafeAreaView>
    );
}
