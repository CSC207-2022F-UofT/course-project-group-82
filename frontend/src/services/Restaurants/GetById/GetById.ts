import { OuiClient } from "../../../networking/OuiClient";
import { RestaurantInterface } from "../RestaurantInterface";

export async function getRestaurantByIdService(restaurantId: string) {
    let response = await OuiClient.get("/getRestaurantsById", {
        id: restaurantId,
    });
    if (!response.successful) {
        console.error(
            "Error getting restaurant: " + response.getErrorMessage()
        );
        console.log("Parameters: " + JSON.stringify(response));
    }
    if (response.getData() == null) {
        console.error("Restaurant not found");
    }
    return response.getData();
}
