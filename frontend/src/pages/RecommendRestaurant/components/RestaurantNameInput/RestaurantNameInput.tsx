import { TextField, Picker} from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function RestaurantNameInput(props: {
    restaurantName: string;
    restaurantNameChange: (text: string) => void;
    // CHECK selection functionality
}) {
    const styles = StyleSheet.create({
        heading: {
            fontSize: 17,
            // CHECK
        }
    })

    return (
        <Picker>
            value={this.state.value}
            placeholder={'Select a restaurant'}
            onChange={props.restaurantNameChange}
            searchPlaceholder={"Search restaurants..."}
            showSearch={true}
            mode=SINGLE
            {/*mode SINGLE disables multiselect*/}
            {_.map(items, item => (
                return this.renderItem(item, index);
                ))}
            migrate
        </Picker>
    )
}