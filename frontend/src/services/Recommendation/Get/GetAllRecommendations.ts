import { OuiClient } from "../../../networking/OuiClient";
import { RecommendationInterface } from "../RecommendationInterface";

export async function getAllRecommendationsService() {
    let response = await OuiClient.get("/getAllRestaurantRecommendations");
    if (!response.successful) {
        console.error(
            "Error posting recommendation: " + response.getErrorMessage()
        );
        console.log("Parameters: " + JSON.stringify(response));
    }
    return response.getData();
}
