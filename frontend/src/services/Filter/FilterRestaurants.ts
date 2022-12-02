import { Filter } from "../../data_types";
import { OuiClient } from "../../networking/OuiClient";

export async function getFilteredRestaurants(filter: Filter) {
    let response = await OuiClient.post("/filterRestaurant", filter);
    if (!response.successful) {
        console.error(
            "Error posting recommendation: " + response.getErrorMessage()
        );
        console.log("Parameters: " + JSON.stringify(response));
    }
    return response.getData();
}
