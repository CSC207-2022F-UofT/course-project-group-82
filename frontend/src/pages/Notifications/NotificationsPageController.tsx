import { NotificationsPageView } from "./NotificationsPageView";
import { OuiNotification } from "./types/OuiNotification";
import { UserPreviewInterface } from "../../services/User/UserInterface";

export function NotificationsPageController(props: {
    navigation: any;
    notifications: OuiNotification[];
    setNotifications: (notifications: OuiNotification[]) => void;
    modalVisible: boolean;
    setModalVisible: (modalVisible: boolean) => void;
    loading: boolean;
    setLoading: (loading: boolean) => void;
    users: Array<UserPreviewInterface>;
    setUsers: (users: Array<UserPreviewInterface>) => void;
}) {
    function closeModal() {
        props.setModalVisible(false);
    }

    function openModal() {
        props.setModalVisible(true);
    }

    const viewProps = {
        navigation: props.navigation,
        notifications: props.notifications,
        closeModal: closeModal,
        openModal: openModal,
        modalVisible: props.modalVisible,
        loading: props.loading,
        users: props.users,
    };

    return <NotificationsPageView {...viewProps} />;
}
