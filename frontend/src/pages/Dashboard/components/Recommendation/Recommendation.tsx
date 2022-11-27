import { ImageBackground, Text, View } from "react-native";
import { Avatar, Divider } from "react-native-paper";
import { OuiRecommendations } from "../../../../data_types";

export function Recommendation(props: { recommendation: OuiRecommendations }) {
    return (
        <>
            {/* Single Recommendation */}
            <View
                className={
                    "w-full flex flex-col my-5 p-3 gap-y-2 border border-gray-100 rounded-lg"
                }
            >
                {/*Recommender Row*/}
                <View className={"flex flex-row w-full gap-3"}>
                    {/*Recommender Avatar*/}
                    <View className={"flex flex-row items-center"}>
                        {/* Sample profile uri: https://gravatar.com/avatar/35cc0f86ca226c87e0d75b711d0601f7?s=400&d=robohash&r=x*/}
                        <Avatar.Image
                            size={30}
                            source={{
                                uri: props.recommendation
                                    .recommendedByProfilePictureLink!,
                            }}
                        />
                    </View>

                    {/*Recommender Name*/}
                    <View className={"flex flex-row justify-start flex-1"}>
                        <View className={"flex flex-col justify-start"}>
                            <Text className={"text-xl font-bold"}>
                                {props.recommendation.recommendedByName}
                            </Text>
                            <Text className={"text-md text-gray-500"}>
                                @{props.recommendation.recommendedByUsername}
                            </Text>
                        </View>
                    </View>
                </View>

                {/*Review posting*/}
                <View>
                    <Text className={"text-xs text-gray-500"}>
                        {props.recommendation.timestamp}
                    </Text>
                </View>

                {/*Divider*/}
                <View>
                    <Divider />
                </View>

                {/*Recommendation Title*/}
                <View className={"flex flex-row items-end gap-x-1"}>
                    {props.recommendation.recommends && (
                        <Text className={"text-lg text-green-600 font-bold"}>
                            Recommends
                        </Text>
                    )}
                    {!props.recommendation.recommends && (
                        <Text className={"text-lg text-red-600 font-bold"}>
                            Does not recommend
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
                    <Text className={"text-md text-gray-500"}>
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
                    <Text className={"text-lg font-bold"}>for</Text>
                </View>

                {/*Divider*/}
                <View>
                    <Divider />
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
