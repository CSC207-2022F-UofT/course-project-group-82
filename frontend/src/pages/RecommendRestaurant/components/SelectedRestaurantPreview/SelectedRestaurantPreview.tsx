import { ImageBackground, Text, TouchableOpacity, View } from "react-native";
import React from "react";
import { RestaurantInterface } from "../../../../services/Restaurants/RestaurantInterface";

export function SelectedRestaurantPreview(props: {
    openModal: () => void;
    selectedRestaurant: RestaurantInterface | null;
    opinionChange: (input: boolean) => void;
}) {
    let address = "Undefined";
    if (props.selectedRestaurant && props.selectedRestaurant.location) {
        address = props.selectedRestaurant.location.display_address.join(", ");
        address = address.replaceAll(", Canada", "");
    }

    return (
        <TouchableOpacity onPress={props.openModal}>
            <Text className={"text-xl font-bold"}>
                {(props.selectedRestaurant &&
                    props.selectedRestaurant["name"]) ||
                    "Select a restaurant"}
            </Text>
            {props.selectedRestaurant &&
                props.selectedRestaurant.image_url &&
                props.selectedRestaurant.location && (
                    <View className={"flex flex-col"}>
                        <Text className={"text-md text-gray-500"}>
                            {address}
                        </Text>
                        <View
                            className={"h-40 rounded-lg shadow-sm bg-red-100"}
                        >
                            <ImageBackground
                                source={{
                                    uri: props.selectedRestaurant.image_url,
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
