import { OuiClient } from "../../../networking/OuiClient";
import { OuiNotification } from "../../../pages/Notifications/types/OuiNotification";
import { getUserDataFromIdService } from "../GetById/GetById";
import { UserInterface } from "../UserInterface";

export async function sendFriendRequestToRecipientFromUser(
    recipientID: string,
    recipientName: string,
    userID: string
) {
    if (userID) {
        let userData = await getUserDataFromIdService(userID);
        let user = (userData as any)["currentUserInfo"] as UserInterface;

        let notification: OuiNotification = {
            recipientId: recipientID,
            recipientName: recipientName,
            senderId: userID,
            senderName: user.username,
            senderProfilePictureLink: user.profilePictureLink,
            type: "create-friend-request",
        };

        let response = await OuiClient.post(
            "/createFriendRequest",
            notification
        );
        if (!response.successful) {
            console.error("Failed to send friend request to user");
        }
        return;
    } else {
        console.error("User ID is undefined - SendFriendRequest");
        return;
    }
}
