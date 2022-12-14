import { Button } from "react-native-ui-lib";
import { Text, View } from "react-native";
import { useLinkTo } from "@react-navigation/native";

export function FormCompletionInput(props: { doLogin: () => void }) {
    const linkTo = useLinkTo();

    return (
        <View className={"gap-3 mt-1"}>
            <Button
                onPress={props.doLogin}
                label={"Log in"}
                size={Button.sizes.medium}
                backgroundColor={"#FFB700"}
            />
            <Text className={"text-center"}>Don't have an account?</Text>
            <Button
                label={"Sign up"}
                link
                linkColor={"#FFB700"}
                onPress={() => {
                    linkTo("/Register");
                }}
            />
        </View>
    );
}
