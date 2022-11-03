import { useState } from "react";
import { RegisterPageController } from "./RegisterPageController";

export function RegisterPageModel(props: { navigation: any }) {
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [errorVisible, setErrorVisible] = useState<boolean>(false);
  const [errors, setErrors] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  const controllerProps = {
    firstName,
    setFirstName,
    lastName,
    setLastName,
    username,
    setUsername,
    password,
    setPassword,
    email,
    setEmail,
    errorVisible,
    setErrorVisible,
    errors,
    setErrors,
    loading,
    setLoading,
  };

  return (
    <RegisterPageController
      {...controllerProps}
      navigation={props.navigation}
    />
  );
}
