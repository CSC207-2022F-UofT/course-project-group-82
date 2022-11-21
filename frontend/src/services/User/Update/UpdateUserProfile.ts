import { UpdateProfileInterface } from "../UpdateProfileInterface";
import { OuiClient } from "../../../networking/OuiClient";
import { setItemAsync } from "expo-secure-store";

export async function doUpdateUserProfile(
    userProfile: UpdateProfileInterface,
    setUserID: (userID: string | null) => void
) {
    const response = await OuiClient.post("/updateUserDetails", userProfile);

    if (!response.successful) {
        console.error("Failed to update user profile");
        console.error(response);
    }

    // Log the user out
    if (response.successful) {
        setUserID(null);
        await setItemAsync("userToken", "");
    }

    return response.getData();
}
