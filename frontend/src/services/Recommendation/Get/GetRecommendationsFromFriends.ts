import { OuiClient } from "../../../networking/OuiClient";

export async function getRecommendationsFromFriendsService(userID: string) {
    let response = await OuiClient.get("/getRestaurantRecommendationsForUser", {
        userId: userID,
    });
    if (!response.successful) {
        console.error(
            "Error posting recommendation: " + response.getErrorMessage()
        );
        console.log("Parameters: " + JSON.stringify(response));
    }
    return response.getData();
}
