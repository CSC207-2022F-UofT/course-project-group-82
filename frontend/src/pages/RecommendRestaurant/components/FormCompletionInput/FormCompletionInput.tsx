import { Button } from "react-native-ui-lib";
import { View } from "react-native";


export function FormCompletionInput(props: { doRecommend: () => void }) {


    return (
        <View className={"gap-3 mt-1"}>
            <Button
                onPress={props.doRecommend}
                label={"Post recommendation"}
                size={Button.sizes.medium}
                backgroundColor={"#FFB700"}
            />
        </View>
    );
}
