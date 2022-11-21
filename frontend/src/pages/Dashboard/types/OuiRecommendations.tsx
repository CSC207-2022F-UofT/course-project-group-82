export interface OuiRecommendations {
    id: number;
    recommendedByName: string;
    recommendedByUsername: string;
    recommendedByProfilePictureLink: string | null;
    timestamp: string;
    recommends: boolean;
    restaurantName: string;
    restaurantAddress: string;
    restaurantImageLink: string;
    restaurantForTags: string[];
}
