import { OuiClient } from "../../../networking/OuiClient";
import { UserPreviewInterface } from "../UserInterface";

export async function getFriendsRequestService(userID: string) {
    if (!userID) {
        console.error("User ID is undefined - GetFriendsRequest");
        return [];
    }

    let response = await OuiClient.get("/getFriends/", { userId: userID });
    if (!response.successful) {
        console.error("Failed to get friends");
        return [];
    }
    return (response as any).getData()["friends"] as UserPreviewInterface[];
}
