import { OuiClient } from "../../../networking/OuiClient";

export async function getUsersByUsername(username: string, userId: string) {
    let response = await OuiClient.get("/getUsersByUsername", {
        username: username,
        userId: userId,
    });

    if (!response.successful) {
        console.error("Failed to get usernames for this input text");
    }

    return response.getData();
}
