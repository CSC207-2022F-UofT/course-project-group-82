import { NotificationsPageView } from "./NotificationsPageView";
import { OuiNotification } from "./types/OuiNotification";

export function NotificationsPageController(props: {
    navigation: any;
    notifications: OuiNotification[];
    setNotifications: any;
}) {
    const viewProps = {
        navigation: props.navigation,
        notifications: props.notifications,
    };

    return <NotificationsPageView {...viewProps} />;
}
