import { ImageBackground, Text, TouchableOpacity, View } from "react-native";
import React from "react";

export function SelectedRestaurantPreview(props: {
    openModal: () => void;
    selectedRestaurant: any;
    opinionChange: (input: boolean) => void;
}) {
    return (
        <TouchableOpacity onPress={props.openModal}>
            <Text className={"text-xl font-bold"}>
                {props.selectedRestaurant["name"] || "Select a restaurant"}
            </Text>
            {props.selectedRestaurant &&
                props.selectedRestaurant.photos &&
                props.selectedRestaurant.address && (
                    <View className={"flex flex-col"}>
                        <Text className={"text-md text-gray-500"}>
                            {props.selectedRestaurant.address.streetAddress +
                                ", " +
                                props.selectedRestaurant.address.zipCode}
                        </Text>
                        <View
                            className={"h-40 rounded-lg shadow-sm bg-red-100"}
                        >
                            <ImageBackground
                                source={{
                                    uri: props.selectedRestaurant.photos[0],
                                }}
                                style={{ width: "100%", height: "100%" }}
                                imageStyle={{ borderRadius: 10 }}
                            />
                        </View>
                    </View>
                )}
        </TouchableOpacity>
    );
}
