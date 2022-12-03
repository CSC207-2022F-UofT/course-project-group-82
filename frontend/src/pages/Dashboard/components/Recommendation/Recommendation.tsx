import {
    ImageBackground,
    Linking,
    Text,
    TouchableHighlight,
    TouchableOpacity,
    View,
} from "react-native";
import { Avatar, Divider } from "react-native-paper";
import { OuiRecommendations } from "../../../../data_types";
import FeatherIcon from "react-native-vector-icons/Feather";
import IonIcon from "react-native-vector-icons/Ionicons";
import { BlurView } from "expo-blur";
import classNames from "classnames";

export function Recommendation(props: { recommendation: OuiRecommendations }) {
    const withShadow = {
        shadowColor: "#000",
        shadowOpacity: 0.2,
        shadowRadius: 3,
        shadowOffset: {
            height: 5,
            width: 0,
        },
    };

    const tagsClassNames = classNames(
        "w-auto px-3 py-1 rounded-xl",
        { "bg-[#2EC03D]": props.recommendation.recommends },
        { "bg-[#FF5A5A]": !props.recommendation.recommends }
    );

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
                        <TouchableOpacity style={withShadow}>
                            <View
                                className={
                                    "h-12 w-12 rounded-full overflow-hidden"
                                }
                            >
                                <ImageBackground
                                    className={"w-full h-full"}
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
                    <View className={"flex flex-1 flex-row justify-between"}>
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
                            <Text
                                className={
                                    "text-xs font-semibold text-gray-300"
                                }
                            >
                                {props.recommendation.timestamp}
                            </Text>
                        </View>
                    </View>
                </View>

                {/* Recommendation Posted */}
                <View>
                    <Text className={"text-sm text-gray-300"}>
                        posted a review
                    </Text>
                </View>

                {/*Recommendation Title*/}
                <View className={"flex flex-row items-end gap-x-1"}>
                    <Text className={"text-lg font-semibold"}>
                        {props.recommendation.recommendedByName.split(" ")[0]}
                    </Text>
                    {props.recommendation.recommends && (
                        <Text className={"text-lg text-[#2EC03D] font-bold"}>
                            recommends
                        </Text>
                    )}
                    {!props.recommendation.recommends && (
                        <Text className={"text-lg text-[#FF5A5A] font-bold"}>
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
                <TouchableOpacity
                    onPress={() => {
                        const googleLink = `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(
                            props.recommendation.restaurantName +
                                ", " +
                                props.recommendation.restaurantAddress
                        )}`;
                        Linking.openURL(googleLink);
                    }}
                    className={"flex flex-row items-start"}
                >
                    <Text className={"text-xs text-gray-400"}>
                        <IonIcon name={"location-outline"} size={16} />
                        {" " + props.recommendation.restaurantAddress}
                    </Text>
                </TouchableOpacity>

                {/* Restaurant Image */}
                <TouchableOpacity
                    onPress={() =>
                        Linking.openURL(
                            props.recommendation.restaurantWebsiteLink
                        )
                    }
                    className={
                        "h-40 rounded-lg overflow-hidden shadow-2xl p-0.5"
                    }
                >
                    <View className={"h-full rounded-lg overflow-hidden"}>
                        {/*Sample uri: https://mabugeneration.com/wp-content/uploads/2020/10/20201004-mabu-generation-taiwanese-sausage-rice.jpg*/}
                        <ImageBackground
                            source={{
                                uri: props.recommendation.restaurantImageLink,
                            }}
                            style={{ width: "100%", height: "100%" }}
                            className={"overflow-hidden"}
                            imageStyle={{ borderRadius: 10 }}
                        >
                            <View
                                className={"overflow-hidden"}
                                style={{
                                    position: "absolute",
                                    left: 0,
                                    right: 0,
                                    bottom: 0,
                                    justifyContent: "center",
                                    alignItems: "flex-start",
                                    borderRadius: 10,
                                }}
                            >
                                <BlurView className={"w-full"} intensity={10}>
                                    <Text className={"text-white mx-2 p-0.5"}>
                                        {props.recommendation.restaurantName +
                                            " "}
                                        <FeatherIcon
                                            name={"external-link"}
                                            size={15}
                                        />
                                    </Text>
                                </BlurView>
                            </View>
                        </ImageBackground>
                    </View>
                </TouchableOpacity>

                {/* for tag */}
                <View className={"flex flex-row justify-center"}>
                    <Text className={"text-lg"}>for</Text>
                </View>

                {/*Wrapper for tags*/}
                <View
                    className={
                        "flex flex-row gap-x-3 justify-start flex-wrap gap-y-1"
                    }
                >
                    {/*Single tag*/}
                    {props.recommendation.restaurantForTags.map((tag) => (
                        <View className={tagsClassNames}>
                            <Text className={"text-xs text-white"}>{tag}</Text>
                        </View>
                    ))}
                </View>
            </View>
        </>
    );
}
