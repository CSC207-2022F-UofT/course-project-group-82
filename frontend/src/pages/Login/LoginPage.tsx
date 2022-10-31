import classNames from "classnames";
import { View, ImageBackground } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { useContext, useState } from "react";
import BackgroundImage from "./assets/background.png";
import LogoHeader from "./components/LogoHeader";
import UsernameInput from "./components/UsernameInput";
import PasswordInput from "./components/PasswordInput";
import FormCompletionInput from "./components/FormCompletionInput";
import ErrorDisplay from "./components/ErrorDisplay";
import LoginService from "../../services/User/Login";
import { UserContext } from "../../context/UserContext";

export function LoginPage() {
  const viewClassnames = classNames("w-1/2 m-auto h-full");

  const innerViewClassnames = classNames("justify-center h-full w-full");

  const formViewClassnames = classNames("mt-5");

  const [username, setUsername] = useState<string>("");

  function usernameChange(text: string) {
    setUsername(text);
  }

  const [password, setPassword] = useState<string>("");

  function passwordChange(text: string) {
    setPassword(text);
  }

  const [errorVisible, setErrorVisible] = useState<boolean>(false);
  const [errors, setErrors] = useState<string>("");

  const { userID, setUserID } = useContext(UserContext);

  async function doLogin() {
    let response = await LoginService(username, password);
    if (response) {
      setErrorVisible(false);
      setErrors("");
      console.log("Logged in user: " + response);
      setUserID(typeof response === "string" ? response : "--error--" );
    } else {
      setErrors("Invalid username or password");
      setErrorVisible(true);
    }
  }

  return (
    <ImageBackground source={BackgroundImage}>
      <ErrorDisplay
        error={errors}
        errorVisible={errorVisible}
        setErrorVisible={setErrorVisible}
      />
      <SafeAreaView className={"bg-[#f5]"}>
        <View className={viewClassnames}>
          <View className={innerViewClassnames}>
            <LogoHeader />
            <View className={formViewClassnames}>
              <UsernameInput
                username={username}
                usernameChange={usernameChange}
              />
              <PasswordInput
                password={password}
                passwordChange={passwordChange}
              />
              <FormCompletionInput doLogin={doLogin} />
            </View>
          </View>
        </View>
      </SafeAreaView>
    </ImageBackground>
  );
}
