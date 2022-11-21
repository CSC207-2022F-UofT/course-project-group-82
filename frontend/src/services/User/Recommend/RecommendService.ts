import { OuiClient } from "../../../networking/OuiClient";

export async function RecommendService(
    restaurantName: string,
    opinion: boolean
): Promise<boolean | string> {
    let response = await OuiClient.post("/recommend", {
        restaurantName,
        opinion,
    });

    // A successful recommendation post
    return response.successful;
}
