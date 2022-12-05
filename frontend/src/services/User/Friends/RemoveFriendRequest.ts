import { OuiClient } from "../../../networking/OuiClient";

export async function removeFriendService(friendID: string, userID: string) {
    if (!friendID) {
        console.error("Friend ID is undefined - RemoveFriendRequest");
        return;
    }

    let response = await OuiClient.get("/removeFriend", {
        friendId: friendID,
        userId: userID,
    });
    if (!response.successful) {
        console.error("Failed to remove friend");
        return;
    }
    return;
}
