import { NotificationsPageController } from "./NotificationsPageController";
import { useContext, useState } from "react";
import { OuiNotification } from "./types/OuiNotification";
import { UserPreviewInterface } from "../../services/User/UserInterface";
import { UserContext } from "../../context/UserContext";

export function NotificationsPageModel(props: { navigation: any }) {
    const { userID, setUserID } = useContext(UserContext);
    const [notifications, setNotifications] = useState<OuiNotification[]>([]);
    const [modalVisible, setModalVisible] = useState<boolean>(false);
    const [loading, setLoading] = useState<boolean>(false);
    const [users, setUsers] = useState<UserPreviewInterface[]>([]);
    const [searchUserText, setSearchUserText] = useState<string>("");

    const controllerProps = {
        ...props,
        userID,
        setUserID,
        notifications,
        setNotifications,
        modalVisible,
        setModalVisible,
        loading,
        setLoading,
        users,
        setUsers,
        searchUserText,
        setSearchUserText,
    };

    return <NotificationsPageController {...controllerProps} />;
}
