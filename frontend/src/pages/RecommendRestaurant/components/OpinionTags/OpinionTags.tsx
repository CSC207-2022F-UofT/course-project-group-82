import { RestaurantInterface } from "../../../../services/Restaurants/RestaurantInterface";
import { Text, TouchableOpacity, View } from "react-native";

export function OpinionTags(props: {
    selectedRestaurant: RestaurantInterface | null;
    selectedOpinions: Array<number>;
    setSelectedOpinions: (input: Array<number>) => void;
}) {
    if (!props.selectedRestaurant) {
        return (
            <>
                <Text className={"text-lg"}>No restaurant selected</Text>
            </>
        );
    } else {
        function toggleTag(index: number) {
            if (props.selectedOpinions.includes(index)) {
                // Remove the tag
                props.setSelectedOpinions(
                    props.selectedOpinions.filter((tag) => tag !== index)
                );
            } else {
                // Add the tag
                props.setSelectedOpinions([...props.selectedOpinions, index]);
            }
        }

        return (
            <>
                <View className={"flex flex-1 flex-row h-full gap-x-2"}>
                    {props.selectedRestaurant.categories.map(
                        (category, index) => {
                            const backgroundColor =
                                props.selectedOpinions.includes(index)
                                    ? "bg-[#FFC107] shadow-md"
                                    : "bg-[#E0E0E0]";
                            const textColor = props.selectedOpinions.includes(
                                index
                            )
                                ? "text-[#ffffff]"
                                : "text-[#9E9E9E]";
                            return (
                                <View className={"flex flex-row"}>
                                    <TouchableOpacity
                                        onPress={() => toggleTag(index)}
                                        className={
                                            backgroundColor +
                                            " rounded-lg w-auto h-12"
                                        }
                                    >
                                        <View
                                            className={
                                                "w-full h-full m-auto px-2 flex justify-center"
                                            }
                                        >
                                            <Text className={textColor}>
                                                {category.title}
                                            </Text>
                                        </View>
                                    </TouchableOpacity>
                                </View>
                            );
                        }
                    )}
                </View>
            </>
        );
    }
}
