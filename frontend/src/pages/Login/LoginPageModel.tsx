import { useState } from "react";
import { LoginPageController } from "./LoginPageController";

export function LoginPageModel(props: { navigation: any }) {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [errorVisible, setErrorVisible] = useState<boolean>(false);
    const [errors, setErrors] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);

    const controllerProps = {
        username,
        setUsername,
        password,
        setPassword,
        errorVisible,
        setErrorVisible,
        errors,
        setErrors,
        loading,
        setLoading,
    };

    return <LoginPageController {...controllerProps} />;
}
