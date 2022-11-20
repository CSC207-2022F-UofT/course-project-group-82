import { stringMd5 } from "react-native-quick-md5";
import * as SecureStore from "expo-secure-store";
import { OuiClient } from "../../../networking/OuiClient";

export async function RegisterService(
    firstName: string,
    lastName: string,
    email: string,
    username: string,
    password: string
): Promise<boolean | string> {
    let response = await OuiClient.post("/register", {
        firstName,
        lastName,
        email,
        username,
        password: stringMd5(password),
    });

    // A successful login
    return response.successful;
}
