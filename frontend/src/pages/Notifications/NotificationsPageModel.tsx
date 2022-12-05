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
    const [friends, setFriends] = useState<UserPreviewInterface[]>([]);
    const [searchUserText, setSearchUserText] = useState<string>("");
    const [refreshing, setRefreshing] = useState<boolean>(false);
    const [refreshingFriends, setRefreshingFriends] = useState<boolean>(false);

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
        friends,
        setFriends,
        searchUserText,
        setSearchUserText,
        refreshing,
        setRefreshing,
        refreshingFriends,
        setRefreshingFriends,
    };

    return <NotificationsPageController {...controllerProps} />;
}
