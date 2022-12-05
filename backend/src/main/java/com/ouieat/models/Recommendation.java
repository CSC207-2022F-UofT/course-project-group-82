package com.ouieat.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("recommendations")
public class Recommendation {

    private String userId;
    private String restaurantId;
    private String postDate;
    private String[] recommendationTags;
    private boolean recommends;

    public Recommendation(
        String userId,
        String postDate,
        String restaurantId,
        String[] recommendationTags,
        boolean recommends
    ) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.postDate = postDate;
        this.recommendationTags = recommendationTags;
        this.recommends = recommends;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRestaurantId() {
        return this.restaurantId;
    }

    public String getDate() {
        return this.postDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public boolean isRecommends() {
        return recommends;
    }

    public void setRecommends(boolean recommends) {
        this.recommends = recommends;
    }

    public String[] getRecommendationTags() {
        return recommendationTags;
    }

    public void setRecommendationTags(String[] recommendationTags) {
        this.recommendationTags = recommendationTags;
    }
}
