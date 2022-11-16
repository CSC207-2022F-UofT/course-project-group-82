import { OuiRequest } from "../../../networking/OuiRequest";
import { stringMd5 } from "react-native-quick-md5";
import * as SecureStore from "expo-secure-store";

export async function RegisterService(
    firstName: string,
    lastName: string,
    email: string,
    username: string,
    password: string
): Promise<boolean | string> {
    let response = await OuiRequest.makeRequest(
        "/register",
        {
            firstName,
            lastName,
            email,
            username,
            password: stringMd5(password),
        },
        "post"
    );

    // After the response has been parsed:
    console.log("Status: " + response.status);

    // A successful login
    return response.status === "success";
}
