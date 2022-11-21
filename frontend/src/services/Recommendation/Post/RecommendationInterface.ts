export interface RecommendationInterface {
    userId: string;
    restaurantId: string;
    rating: number;
    review: string;
    postDate: string;
    recommends: boolean;
}
