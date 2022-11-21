import { OuiClient } from "../../../networking/OuiClient";
import { RecommendationInterface } from "./RecommendationInterface";

export async function doPostRecommendationService(
    recommendation: RecommendationInterface
): Promise<boolean> {
    let response = await OuiClient.post(
        "/postRestaurantRecommendation",
        recommendation
    );
    if (!response.successful) {
        console.error(
            "Error posting recommendation: " + response.getErrorMessage()
        );
        console.log("Parameters: " + JSON.stringify(recommendation));
    }
    return response.successful;
}
