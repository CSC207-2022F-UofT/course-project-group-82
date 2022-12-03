import { Text, TouchableOpacity, View } from "react-native";
import React from "react";
import classNames from "classnames";

export function OpinionToggle(props: {
    opinionChange: (input: boolean) => void;
    opinion: boolean;
}) {
    const recommendClassNames = classNames({
        "bg-green-100 ": props.opinion,
        "bg-gray-100": !props.opinion,
        "flex flex-row flex-1 justify-center items-center rounded-lg": true,
    });

    const notRecommendClassNames = classNames({
        "bg-red-100 ": !props.opinion,
        "bg-gray-100": props.opinion,
        "flex flex-row flex-1 justify-center items-center rounded-lg": true,
    });

    return (
        <View className={"flex flex-row"}>
            <View className={"h-24 w-full flex-row gap-2"}>
                <TouchableOpacity
                    onPress={() => props.opinionChange(true)}
                    className={recommendClassNames}
                >
                    <View>
                        <Text
                            className={!props.opinion ? "text-[#9E9E9E]" : ""}
                        >
                            I recommend this restaurant
                        </Text>
                    </View>
                </TouchableOpacity>
                <TouchableOpacity
                    onPress={() => props.opinionChange(false)}
                    className={notRecommendClassNames}
                >
                    <View>
                        <Text className={props.opinion ? "text-[#9E9E9E]" : ""}>
                            I don't recommend this restaurant
                        </Text>
                    </View>
                </TouchableOpacity>
            </View>
        </View>
    );
}
