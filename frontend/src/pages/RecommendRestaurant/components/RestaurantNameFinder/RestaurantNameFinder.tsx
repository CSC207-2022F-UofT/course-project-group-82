import { TextField, Picker, Button as RNButton } from "react-native-ui-lib";
import {
    ImageBackground,
    Modal,
    SafeAreaView,
    ScrollView,
    StyleSheet,
    Text,
    TouchableOpacity,
    View,
} from "react-native";
import React from "react";
import { RestaurantInterface } from "../../../../services/Restaurants/RestaurantInterface";

export function RestaurantNameFinder(props: {
    modalVisible: boolean;
    loading: boolean;
    closeModal: () => void;
    restaurants: Array<RestaurantInterface>;
    restaurantName: string;
    updateSearchField: (text: string) => void;
    searchRestaurant: () => void;
    changeRestaurantSelected: (item: RestaurantInterface) => void;
}) {
    const styles = StyleSheet.create({
        withUnderline: {
            backgroundColor: "#DEE3E7",
            borderRadius: 10,
            padding: 10,
        },
    });

    return (
        <Modal
            animationType="slide"
            transparent={true}
            visible={props.modalVisible}
        >
            <SafeAreaView className={"h-full w-full"}>
                <View className={"bg-[#ffffff] flex flex-1 px-5 pt-2"}>
                    <View className={"flex flex-row gap-2"}>
                        <View className={"flex flex-col flex-1"}>
                            <TextField
                                placeholder={"Search for a restaurant"}
                                fieldStyle={styles.withUnderline}
                                value={props.restaurantName}
                                onChangeText={props.updateSearchField}
                                maxLength={16}
                                migrate
                            />
                        </View>
                        <RNButton
                            label={"Search"}
                            borderRadius={10}
                            backgroundColor={"#FFB700"}
                            size={"small"}
                            onPress={props.searchRestaurant}
                        />
                    </View>
                    {props.restaurants && props.restaurants.length > 0 ? (
                        <ScrollView>
                            {props.restaurants.map((restaurant) => (
                                <TouchableOpacity
                                    onPress={() => {
                                        props.changeRestaurantSelected(
                                            restaurant
                                        );
                                        props.closeModal();
                                    }}
                                    className={"flex flex-col"}
                                >
                                    <View className={"flex flex-col my-1"}>
                                        <Text
                                            className={"text-lg font-semibold"}
                                        >
                                            {restaurant.name}
                                        </Text>
                                        <Text
                                            className={"text-md text-gray-500"}
                                        >
                                            {restaurant.location.display_address
                                                .join(", ")
                                                .replaceAll(", Canada", "")}
                                        </Text>
                                    </View>
                                    <View
                                        className={
                                            "h-40 rounded-lg shadow-sm bg-red-100"
                                        }
                                    >
                                        <ImageBackground
                                            source={{
                                                uri: restaurant.image_url,
                                            }}
                                            style={{
                                                width: "100%",
                                                height: "100%",
                                            }}
                                            imageStyle={{ borderRadius: 10 }}
                                        />
                                    </View>
                                </TouchableOpacity>
                            ))}
                        </ScrollView>
                    ) : (
                        <View
                            className={
                                "flex flex-1 justify-center items-center"
                            }
                        >
                            <Text
                                className={"text-lg font-semibold font-italic"}
                            >
                                {props.loading
                                    ? "Loading..."
                                    : "No restaurants found"}
                            </Text>
                        </View>
                    )}
                </View>
                <View className={"flex flex-col"}>
                    <RNButton
                        backgroundColor={"#FFB700"}
                        borderRadius={10}
                        label={"Close"}
                        onPress={props.closeModal}
                    />
                </View>
            </SafeAreaView>
        </Modal>
    );
}
