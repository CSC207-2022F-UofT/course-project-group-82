import { OuiNotification } from "../../../pages/Notifications/types/OuiNotification";
import { OuiClient } from "../../../networking/OuiClient";

export async function handleFriendRequestService(
    notification: OuiNotification,
    accept: boolean
) {
    let responseUrl = accept ? "/acceptFriendRequest" : "/declineFriendRequest";
    let response = await OuiClient.post(responseUrl, notification);
    if (!response.successful) {
        console.error("Failed to handle friend request");
        return;
    }
    return;
}
