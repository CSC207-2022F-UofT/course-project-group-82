import { ImageBackground, Text, TouchableOpacity, View } from "react-native";
import { Avatar, Divider } from "react-native-paper";
import { OuiRecommendations } from "../../../../data_types";

export function Recommendation(props: { recommendation: OuiRecommendations }) {
    return (
        <>
            {/* Single Recommendation */}
            <View
                className={
                    "w-full flex flex-col my-5 gap-y-2 p-3 border border-gray-300 rounded-lg"
                }
            >
                {/*Recommender Row*/}
                <View className={"flex flex-row w-full"}>
                    {/*Recommender Avatar*/}
                    <View className={"flex flex-row items-center mr-2"}>
                        {/* Sample profile uri: https://gravatar.com/avatar/35cc0f86ca226c87e0d75b711d0601f7?s=400&d=robohash&r=x*/}
                        <TouchableOpacity>
                            <View className={"h-12 w-12"}>
                                <ImageBackground
                                    imageStyle={{
                                        borderRadius: 999,
                                        shadowRadius: 999,
                                    }}
                                    className={"w-full h-full shadow-lg"}
                                    source={{
                                        uri:
                                            props.recommendation
                                                .recommendedByProfilePictureLink ||
                                            "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y",
                                    }}
                                />
                            </View>
                        </TouchableOpacity>
                    </View>

                    {/*Recommender Name*/}
                    <View
                        className={
                            "flex flex-1 flex-row items-baseline justify-between"
                        }
                    >
                        <View className={"flex flex-col gap-y-[-3]"}>
                            <Text className={"text-xl font-semibold"}>
                                {props.recommendation.recommendedByName}
                            </Text>
                            <Text
                                className={
                                    "text-sm text-gray-400 font-semibold"
                                }
                            >
                                @{props.recommendation.recommendedByUsername}
                            </Text>
                        </View>
                        <View className={"flex flex-col"}>
                            <Text className={"text-xs text-gray-400"}>
                                {props.recommendation.timestamp}
                            </Text>
                        </View>
                    </View>
                </View>

                {/* Recommendation Posted */}
                <View>
                    <Text className={"text-sm text-gray-400"}>
                        posted a review
                    </Text>
                </View>

                {/*Recommendation Title*/}
                <View className={"flex flex-row items-end gap-x-1"}>
                    <Text className={"text-lg font-semibold"}>
                        {props.recommendation.recommendedByName.split(" ")[0]}
                    </Text>
                    {props.recommendation.recommends && (
                        <Text className={"text-lg text-green-600 font-bold"}>
                            recommends
                        </Text>
                    )}
                    {!props.recommendation.recommends && (
                        <Text className={"text-lg text-red-600 font-bold"}>
                            does not recommend
                        </Text>
                    )}
                </View>

                {/* Restaurant Name */}
                <View>
                    <Text className={"text-2xl font-bold"}>
                        {props.recommendation.restaurantName}
                    </Text>
                </View>

                {/* Restaurant Address */}
                <View className={"flex flex-row items-start"}>
                    <Text className={"text-md text-gray-400"}>
                        {props.recommendation.restaurantAddress}
                    </Text>
                </View>

                {/* Restaurant Image */}
                <View className={"h-40 rounded-lg shadow-lg bg-red-100"}>
                    {/*Sample uri: https://mabugeneration.com/wp-content/uploads/2020/10/20201004-mabu-generation-taiwanese-sausage-rice.jpg*/}
                    <ImageBackground
                        source={{
                            uri: props.recommendation.restaurantImageLink,
                        }}
                        style={{ width: "100%", height: "100%" }}
                        imageStyle={{ borderRadius: 10 }}
                    />
                </View>

                {/* for tag */}
                <View className={"flex flex-row justify-center"}>
                    <Text className={"text-lg"}>for</Text>
                </View>

                {/*Wrapper for tags*/}
                <View
                    className={
                        "flex flex-row gap-x-3 gap-y-1 justify-start flex-wrap"
                    }
                >
                    {/*Single tag*/}
                    {props.recommendation.restaurantForTags.map((tag) => (
                        <View
                            className={
                                "w-auto bg-[#ffb700] px-3 py-1 rounded-xl"
                            }
                        >
                            <Text className={"text-xs text-white"}>{tag}</Text>
                        </View>
                    ))}
                </View>
            </View>
        </>
    );
}
