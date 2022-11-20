import { NotificationsPageController } from "./NotificationsPageController";
import { useState } from "react";
import { OuiNotification } from "./types/OuiNotification";
import { OuiRecommendations } from "../Dashboard/types/OuiRecommendations";

export function NotificationsPageModel(props: { navigation: any }) {
    const [notifications, setNotifications] = useState<OuiNotification[]>([]);

    const controllerProps = {
        ...props,
        notifications,
        setNotifications,
    };

    return <NotificationsPageController {...controllerProps} />;
}
