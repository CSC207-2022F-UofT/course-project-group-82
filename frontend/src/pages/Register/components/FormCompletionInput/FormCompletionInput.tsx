import { Button } from "react-native-ui-lib";
import { Text, View } from "react-native";
import { useLinkTo } from "@react-navigation/native";

export function FormCompletionInput(props: { doRegister: () => void }) {
    const linkTo = useLinkTo();

    return (
        <View className={"gap-3 mt-1"}>
            <Button
                onPress={props.doRegister}
                label={"Register"}
                size={Button.sizes.medium}
                backgroundColor={"#FFB700"}
            />
            <Text className={"text-center"}>Already have an account?</Text>
            <Button
                label={"Log in"}
                link
                linkColor={"#FFB700"}
                onPress={() => {
                    linkTo("/Home");
                }}
            />
        </View>
    );
}
