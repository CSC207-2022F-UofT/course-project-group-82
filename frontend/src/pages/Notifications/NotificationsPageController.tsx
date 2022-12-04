import { NotificationsPageView } from "./NotificationsPageView";
import { OuiNotification } from "./types/OuiNotification";
import { UserPreviewInterface } from "../../services/User/UserInterface";
import { getUsersByUsername } from "../../services/User/GetByUsername/GetByUsername";

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
}) {
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
    };

    return <NotificationsPageView {...viewProps} />;
}
