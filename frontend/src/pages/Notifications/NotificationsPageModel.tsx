import { NotificationsPageController } from "./NotificationsPageController";
import { useState } from "react";
import { OuiNotification } from "./types/OuiNotification";

export function NotificationsPageModel(props: { navigation: any }) {
    const [notifications, setNotifications] = useState<OuiNotification[]>([]);

    const controllerProps = {
        ...props,
        notifications,
        setNotifications,
    };

    return <NotificationsPageController {...controllerProps} />;
}
