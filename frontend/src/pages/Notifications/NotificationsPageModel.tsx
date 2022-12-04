import { NotificationsPageController } from "./NotificationsPageController";
import { useState } from "react";
import { OuiNotification } from "./types/OuiNotification";
import { UserPreviewInterface } from "../../services/User/UserInterface";

export function NotificationsPageModel(props: { navigation: any }) {
    const [notifications, setNotifications] = useState<OuiNotification[]>([]);
    const [modalVisible, setModalVisible] = useState<boolean>(false);
    const [loading, setLoading] = useState<boolean>(false);
    const [users, setUsers] = useState<UserPreviewInterface[]>([]);

    const controllerProps = {
        ...props,
        notifications,
        setNotifications,
        modalVisible,
        setModalVisible,
        loading,
        setLoading,
        users,
        setUsers,
    };

    return <NotificationsPageController {...controllerProps} />;
}
