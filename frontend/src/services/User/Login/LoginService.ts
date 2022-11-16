import { OuiRequest } from "../../../networking/OuiRequest";
import * as SecureStore from "expo-secure-store";
import { stringMd5 } from "react-native-quick-md5";
export async function loginService(
    username: string,
    password: string
): Promise<boolean | string> {
    // We assume that once the data reaches the services
    // It has already undergone form validation
    // And the data is ready to be made a request

    let response = await OuiRequest.makeRequest(
        "/login",
        {
            username: username,
            password: stringMd5(password),
        },
        "post"
    );

    // After the response has been parsed:
    console.log("Status: " + response.status);

    // A successful login
    if (response.status === "success") {
        // Login was successful
        // Save the user token to a secure store context
        let userID = response.data!.id;
        await SecureStore.setItemAsync("userToken", userID);
        return userID;
    }
    // Failed to login
    else {
        return false;
    }
}
