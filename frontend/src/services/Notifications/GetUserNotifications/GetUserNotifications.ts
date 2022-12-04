import { OuiClient } from "../../../networking/OuiClient";
import { OuiNotification } from "../../../pages/Notifications/types/OuiNotification";
import assert from "assert";

export async function getUserNotifications(
    userID: string
): Promise<OuiNotification[]> {
    let response = await OuiClient.get("/notifications", { userId: userID });

    if (!response.successful) {
        console.error("Failed to get user notifications");
        return [];
    }

    let data = response.getData() as any;
    return data["notifications"] as OuiNotification[];
}
