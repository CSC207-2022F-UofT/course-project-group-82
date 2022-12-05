export interface RecommendationInterface {
    userId: string;
    restaurantId: string;
    postDate: string;
    recommends: boolean;
    recommendationTags: string[];
}
