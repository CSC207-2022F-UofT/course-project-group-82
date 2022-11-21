import { RadioButton, RadioGroup, Switch } from "react-native-ui-lib";
import { StyleSheet } from "react-native";

export function OpinionButton(props: {
    opinion: boolean;
    opinionChange: (input: boolean) => void;
}) {
    const styles = StyleSheet.create({});

    return (
        <RadioGroup
            // consider making disabled when no restaurant name selected
            initialValue={true} // take out later to make null by default
            onValueChange={props.opinionChange} // need to be added to RadioButtons?
            migrate
        >
            <RadioButton
                value={true}
                color={"#256C5B"}
                // add icons here later
                label={"I recommend this restaurant"}
                migrate
            />
            <RadioButton
                value={false}
                color={"#FF4848"}
                // add icons here later
                label={"I don't recommend this restaurant"}
                migrate
            />
        </RadioGroup>
    );
}
