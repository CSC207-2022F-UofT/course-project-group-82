import { NotificationsPageView } from "./NotificationsPageView";
import { OuiNotification } from "./types/OuiNotification";
import { UserPreviewInterface } from "../../services/User/UserInterface";
import { getUsersByUsername } from "../../services/User/GetByUsername/GetByUsername";
import { sendFriendRequestToRecipientFromUser } from "../../services/User/Friends/SendFriendRequest";
import { useCallback, useEffect } from "react";
import { getUserNotifications } from "../../services/Notifications/GetUserNotifications/GetUserNotifications";
import { handleFriendRequestService } from "../../services/User/Friends/HandleFriendRequest";
import { getFriendsRequestService } from "../../services/User/Friends/GetFriendsRequest";
import { removeFriendService } from "../../services/User/Friends/RemoveFriendRequest";

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
    friends: Array<UserPreviewInterface>;
    setFriends: (friends: Array<UserPreviewInterface>) => void;
    searchUserText: string;
    setSearchUserText: (searchUserText: string) => void;
    refreshing: boolean;
    setRefreshing: (refreshing: boolean) => void;
    refreshingFriends: boolean;
    setRefreshingFriends: (refreshingFriends: boolean) => void;
}) {
    useEffect(() => {
        refreshNotifications();
        refreshFriendsList();
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

    async function handleFriendRequest(
        notification: OuiNotification,
        accept: boolean
    ) {
        props.setRefreshing(true);
        await handleFriendRequestService(notification, accept);
        await refreshNotifications();
        props.setRefreshing(false);
    }

    async function handleRemoveFriend(friendId: string) {
        if (!props.userID) return;
        props.setRefreshingFriends(true);
        await removeFriendService(friendId, props.userID);
        await refreshFriendsList();
        props.setRefreshingFriends(false);
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
        await refreshFriendsList();
        props.setRefreshing(false);
    }, []);

    const refreshFriendsList = useCallback(async () => {
        props.setRefreshingFriends(true);
        if (!props.userID) {
            console.error("User ID is null");
            props.setRefreshing(false);
            return;
        }
        let newFriends = await getFriendsRequestService(props.userID);
        props.setFriends(newFriends);
        props.setRefreshingFriends(false);
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
        friends: props.friends,
        updateSearchUserText: updateSearchUserText,
        searchForUsersByUsername,
        sendFriendRequest,
        refreshing: props.refreshing,
        refreshingFriends: props.refreshingFriends,
        refreshNotifications,
        refreshFriendsList,
        handleFriendRequest,
        handleRemoveFriend,
    };

    return <NotificationsPageView {...viewProps} />;
}
