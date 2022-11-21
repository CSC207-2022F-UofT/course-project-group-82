import { View, SafeAreaView } from "react-native";
import { MaterialIcons } from "@expo/vector-icons";
import SectionedMultiSelect from "react-native-sectioned-multi-select";
import { Button } from "react-native-ui-lib";
import Navbar from "../../components/Navbar";

export function FindRestaurantsView(props: {
    navigation: any;
    restaurantInput: string;
    updateRestaurantInput: (text: string) => void;
    expanded: boolean;
    handlePress: () => void;
    doLogout: () => void;
    showNotificationsPage: () => void;
    searchItems: any[];
    selectedCuisines: any[];
    changeOfCuisinesSelected: any;
}) {
    function filterItemsManually(searchTerm: string) {
        let temp: any[] = [];
        searchTerm = searchTerm.toLowerCase();
        props.searchItems.map((a: any) => {
            if (
                a["searchKeywords"].includes(searchTerm) ||
                a["implicates"].includes(searchTerm) ||
                a["displayName"].includes(searchTerm)
            ) {
                temp.push(a);
            }
        });
        return temp;
    }

    return (
        <SafeAreaView className={"bg-[#ffffff]"}>
            <View className={"h-full w-full flex flex-col"}>
                <Navbar navigation={props.navigation} />

                <View className={"h-full w-full flex-1"}>
                    <SectionedMultiSelect
                        filterItems={filterItemsManually}
                        subKey="children"
                        displayKey={"displayName"}
                        selectText={"Cuisines"}
                        confirmText={"Choose cuisines"}
                        searchPlaceholderText={"Enter a cuisine"}
                        modalWithSafeAreaView={true}
                        modalWithTouchable={true}
                        modalAnimationType={"slide"}
                        showDropDowns={true}
                        showRemoveAll={true}
                        colors={{
                            primary: "#ffb700",
                            searchSelectionColor: "#dee3e7",
                        }}
                        onSelectedItemsChange={props.changeOfCuisinesSelected}
                        selectedItems={props.selectedCuisines}
                        items={props.searchItems}
                        /* @ts-ignore */
                        IconRenderer={MaterialIcons}
                        uniqueKey={"id"}
                    />
                </View>

                <View className={"flex flex-row justify-center w-full"}>
                    <Button
                        iconOnRight={false}
                        borderRadius={10}
                        label={"Find matching restaurants"}
                        size={Button.sizes.large}
                        backgroundColor={"#FFB700"}
                    />
                </View>
            </View>
        </SafeAreaView>
    );
}
