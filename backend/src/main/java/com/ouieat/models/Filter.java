package com.ouieat.models;

import java.lang.reflect.Array;

public class Filter {
  // distance of restaurant from current location
  private float distance;

  // type of food (ex - Indian, Chinese, Italian etc.)
  private String cuisine;

  // contains 2 values- $min and $max
  private Array[] priceRange;
  public Filter(float distance, String cuisine, Array[] priceRange){
      this.distance = distance;
      this.cuisine = cuisine;
      this.priceRange = priceRange;
  }

  public void setDistance(float distance){
      this.distance = distance;
  }
  public float getDistance(){
      return distance;
  }
  public void setCuisine(String cuisine){
      this.cuisine = cuisine;
  }
  public Array[] getPriceRange(){
      return priceRange;
  }
  public void setPriceRange(Array[] priceRange){
        this.priceRange = priceRange;
    }

}
