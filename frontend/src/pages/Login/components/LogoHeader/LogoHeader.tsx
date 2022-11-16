import { AnimatedImage, Image } from "react-native-ui-lib";
import LogoImage from "../../assets/smiley.png";
import { Text, View } from "react-native";
import classNames from "classnames";

export function LogoHeader() {
    const titleTextClassnames = classNames("text-2xl text-center text-[#000]");

    return (
        <View>
            <AnimatedImage
                style={{ width: "100%", height: undefined }}
                cover={true}
                aspectRatio={1.25}
                source={LogoImage}
            />
            <Text className={titleTextClassnames}>OuiEat</Text>
        </View>
    );
}
