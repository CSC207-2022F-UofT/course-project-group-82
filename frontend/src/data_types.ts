export type CategoryID = string;

export interface Category {
    id: CategoryID;
    displayName: string;
    searchKeywords: string[];
    implicates: CategoryID[];
}

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

export interface Filter {
    distance: number;

    userLatitude: number;

    userLongitude: number;

    // type of food (ex - Indian, Chinese, Italian etc.)
    categories: CategoryID[];

    // contains 2 values- $min and $max
    priceRangeMin: number;

    priceRangeMax: number;
}
