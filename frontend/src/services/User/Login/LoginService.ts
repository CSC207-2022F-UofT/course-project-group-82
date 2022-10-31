import { OuiRequest } from "../../../networking/OuiRequest";
import * as SecureStore from "expo-secure-store";
export async function LoginService(
  username: string,
  password: string
): Promise<boolean | string> {
  // We assume that once the data reaches the services
  // It has already undergone form validation
  // And the data is ready to be made a request

  let response = await OuiRequest.make(
    "/login",
    {
      username: username,
      password: password,
    },
    "post"
  );

  // After the response has been parsed:
  console.log("Status: " + response.status);

  // A successful login
  if (response.status === "success") {
    // Login was successful
    // Save the user token to a secure store context
    await SecureStore.setItemAsync("userToken", response.data);
    return response.data;
  }
  // Failed to login
  else {
    return false;
  }
}
