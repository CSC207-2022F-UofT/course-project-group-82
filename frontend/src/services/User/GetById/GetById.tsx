import { OuiClient } from "../../../networking/OuiClient";
import { UserInterface } from "../UserInterface";

export async function getUserDataFromIdService(id: string) {
    let response = await OuiClient.get("/dashboard", { userID: id });

    if (!response.successful) {
        console.error("Failed to get user data from id");
    }
    return response.getData();
}
