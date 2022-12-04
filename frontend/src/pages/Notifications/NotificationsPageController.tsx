import { NotificationsPageView } from "./NotificationsPageView";
import { OuiNotification } from "./types/OuiNotification";
import { UserPreviewInterface } from "../../services/User/UserInterface";
import { getUsersByUsername } from "../../services/User/GetByUsername/GetByUsername";
import { sendFriendRequestToRecipientFromUser } from "../../services/User/Friends/SendFriendRequest";
import { useCallback, useEffect } from "react";
import { getUserNotifications } from "../../services/Notifications/GetUserNotifications/GetUserNotifications";

export function NotificationsPageController(props: {
    navigation: any;
    userID: string | null;
    setUserID: (id: string) => void;
    notifications: OuiNotification[];
    setNotifications: (notifications: OuiNotification[]) => void;
    modalVisible: boolean;
    setModalVisible: (modalVisible: boolean) => void;
    loading: boolean;
    setLoading: (loading: boolean) => void;
    users: Array<UserPreviewInterface>;
    setUsers: (users: Array<UserPreviewInterface>) => void;
    searchUserText: string;
    setSearchUserText: (searchUserText: string) => void;
    refreshing: boolean;
    setRefreshing: (refreshing: boolean) => void;
}) {
    useEffect(() => {
        refreshNotifications();
    }, []);

    function closeModal() {
        props.setModalVisible(false);
    }

    function openModal() {
        props.setModalVisible(true);
    }

    function updateSearchUserText(text: string) {
        props.setSearchUserText(text);
    }

    async function searchForUsersByUsername() {
        if (!props.userID) {
            console.error("User ID is null");
            return;
        }
        props.setLoading(true);
        let users = await getUsersByUsername(
            props.searchUserText,
            props.userID
        );
        const usersPreview = (users as any)[
            "users"
        ] as Array<UserPreviewInterface>;
        props.setUsers(usersPreview);
        props.setLoading(false);
    }

    async function sendFriendRequest(
        recipientId: string,
        recipientUsername: string
    ) {
        if (!props.userID) {
            console.error("User ID is null");
            return;
        }
        if (!recipientId) {
            console.error("Recipient ID is null");
            return;
        }
        props.setLoading(true);
        await sendFriendRequestToRecipientFromUser(
            recipientId,
            recipientUsername,
            props.userID
        );
        await searchForUsersByUsername();
        props.setLoading(false);
    }

    const refreshNotifications = useCallback(async () => {
        props.setRefreshing(true);
        if (!props.userID) {
            console.error("User ID is null");
            props.setRefreshing(false);
            return;
        }
        let newNotifications = [] as OuiNotification[];
        newNotifications = await getUserNotifications(props.userID);
        props.setNotifications(newNotifications);
        props.setRefreshing(false);
    }, []);

    const viewProps = {
        navigation: props.navigation,
        notifications: props.notifications,
        closeModal: closeModal,
        openModal: openModal,
        modalVisible: props.modalVisible,
        loading: props.loading,
        users: props.users,
        searchUserText: props.searchUserText,
        updateSearchUserText: updateSearchUserText,
        searchForUsersByUsername,
        sendFriendRequest,
        refreshing: props.refreshing,
        refreshNotifications,
    };

    return <NotificationsPageView {...viewProps} />;
}
