export interface UserInterface {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
    profilePictureLink: string;
    username: string;
    friendIds: string[];
}

export interface UserPreviewInterface {
    id: string;
    firstName: string;
    lastName: string;
    username: string;
    profilePictureLink: string;
}
