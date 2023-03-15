package com.gameberry.resturantrecommendation.services;

import com.gameberry.resturantrecommendation.enums.Cuisine;
import com.gameberry.resturantrecommendation.requests.*;
import com.gameberry.resturantrecommendation.sort.SortCostTracking;
import com.gameberry.resturantrecommendation.sort.SortCuisine;
import com.gameberry.resturantrecommendation.sort.SortingResturant;
import com.gameberry.resturantrecommendation.sort.SortingResturantByRating;
import com.gameberry.resturantrecommendation.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ResturantRecommendationService {

    public List<String> getUserResturantRecommendation(RecommendationRequest recommendationRequest) {
        User user = recommendationRequest.getUser();
        List<Restaurant> restaurants =  recommendationRequest.getAvailableRestaurants();
        List<String> recommendedResturant = new ArrayList<>();

        if(user != null && !restaurants.isEmpty()){

            //check primary cuisine and secondary
            List<CuisineTracking> cuisines = user.getCuisines();
            List<CostTracking> costTrackingList = user.getCostBracket();
            Map<Integer, String> primaryAndSecondaryCuisine = findPrimaryAndSecondaryCuisine(cuisines);
            Map<Integer, String> primaryAndSecondaryCostBracket = findPrimaryAndSecondaryCostBracket(costTrackingList);

            // check case 1
            
            // Featured restaurants of primary cuisine and primary cost bracket
            List<String> primaryCuisineString = createList(primaryAndSecondaryCuisine, Constants.PRIMARY);
            List<String> secondaryCostBracketString = createList(primaryAndSecondaryCostBracket, Constants.PRIMARY);
            filteredRestaurant(primaryCuisineString, secondaryCostBracketString, recommendedResturant, restaurants, false, 1, true, "");

            //if None
            if(recommendedResturant.isEmpty()){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.PRIMARY), createList(primaryAndSecondaryCostBracket, Constants.SECONDARY), recommendedResturant, restaurants, false, 1, true, "");
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.SECONDARY), createList(primaryAndSecondaryCostBracket, Constants.PRIMARY), recommendedResturant, restaurants, false, 1, true, "");
            }

            //check is recommended list is 100 or not

            //case 2
          
            if(recommendedResturant.size() < Constants.LIMIT){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.PRIMARY), createList(primaryAndSecondaryCostBracket, Constants.PRIMARY), recommendedResturant, restaurants, true, 4F, false, Constants.GREATER_THAN_EQUAL_TO);
            }


            
            //case 3

            if(recommendedResturant.size() < Constants.LIMIT){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.PRIMARY), createList(primaryAndSecondaryCostBracket, Constants.SECONDARY), recommendedResturant, restaurants, true, 4.5F, false, Constants.GREATER_THAN_EQUAL_TO);
            }

            //case 4

            if(recommendedResturant.size() < Constants.LIMIT){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.SECONDARY), createList(primaryAndSecondaryCostBracket, Constants.PRIMARY), recommendedResturant, restaurants, true, 4.5F, false, Constants.GREATER_THAN_EQUAL_TO);
            }

           
            //case 5

            if(recommendedResturant.size() < Constants.LIMIT){
                topNewlyCreatedRestaurant(recommendedResturant, restaurants, 4);
            }

            //case 6

            if(recommendedResturant.size() < Constants.LIMIT){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.PRIMARY), createList(primaryAndSecondaryCostBracket, Constants.PRIMARY), recommendedResturant, restaurants, true, 4F, false, Constants.LESS_THAN);
            }

           
            //case 7

            if(recommendedResturant.size() < Constants.LIMIT){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.PRIMARY), createList(primaryAndSecondaryCostBracket, Constants.SECONDARY), recommendedResturant, restaurants, true, 4.5F, false, Constants.LESS_THAN);
            }

            //case 8

            if(recommendedResturant.size() < Constants.LIMIT){
                filteredRestaurant(createList(primaryAndSecondaryCuisine, Constants.SECONDARY), createList(primaryAndSecondaryCostBracket, Constants.PRIMARY), recommendedResturant, restaurants, true, 4.5F, false, Constants.LESS_THAN);
            }


            //add remaining resturants


            if(recommendedResturant.size() < Constants.LIMIT){
                int count = Constants.LIMIT - recommendedResturant.size();
                for(Restaurant restaurant : restaurants){
                    if(count > 0){
                        recommendedResturant.add(restaurant.getRestaurantId());
                        count--;
                    }
                }
            }
        }
        return recommendedResturant;
    }

    private void topNewlyCreatedRestaurant(List<String> recommendedRestaurant, List<Restaurant> restaurants, int topCount) {
        Collections.sort(restaurants, new SortingResturant());
        List<Restaurant> toBeRemove = new ArrayList<>();
        List<Restaurant> restaurantListRegisterAfter48Hour = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -48);
        Date prevDate = cal.getTime();
        for(Restaurant restaurant : restaurants){
            if(restaurant.getOnboardedTime().after(prevDate)){
                restaurantListRegisterAfter48Hour.add(restaurant);
            }
        }

        Collections.sort(restaurantListRegisterAfter48Hour, new SortingResturantByRating());


        for(Restaurant restaurant : restaurantListRegisterAfter48Hour){
            if(topCount > 0){
                recommendedRestaurant.add(restaurant.getRestaurantId());
                toBeRemove.add(restaurant);
                topCount--;
            }
        }
        restaurants.removeAll(toBeRemove);
    }

    private List<String> createList(Map<Integer, String> data, String preference) {
        List<String> list = new ArrayList<>();
        if(preference.equals(Constants.PRIMARY)){
            list.add(data.get(1));
        }else{
            list.add(data.get(2));
            list.add(data.get(3));
        }
        return list;
    }

    private void filteredRestaurant(List<String> cuisine, List<String> costBracket, List<String> recommendedRestaurant, List<Restaurant> restaurantList, boolean ratingApplicable, float rating, boolean isFeatured, String ratingCondition) {
        List<Restaurant> toBeRemove = new ArrayList<>();
        for(Restaurant restaurant : restaurantList){
            if(!ratingApplicable){
                if(isFeatured && restaurant.isRecommended() && cuisine.contains(Cuisine.valueOf(restaurant.getCuisine()).getValue()) && costBracket.contains(String.valueOf(restaurant.getCostBracket()))){
                    recommendedRestaurant.add(restaurant.getRestaurantId());
                    toBeRemove.add(restaurant);
                }
            }else{
                if(cuisine.contains(Cuisine.valueOf(restaurant.getCuisine()).getValue()) && costBracket.contains(String.valueOf(restaurant.getCostBracket())) && checkRatingCondition(rating, ratingCondition, restaurant.getRating())){
                    recommendedRestaurant.add(restaurant.getRestaurantId());
                    toBeRemove.add(restaurant);
                }
            }
        }
        restaurantList.removeAll(toBeRemove);
    }

    private boolean checkRatingCondition(float rating, String ratingCondition, float restaurantRating) {
        int diff = Float.compare(restaurantRating, rating);
        switch (ratingCondition){
            case Constants.GREATER_THAN_EQUAL_TO:
                return diff >= 0;
            case Constants.LESS_THAN:
                return diff < 0;
            default:
                return false;
        }
    }

    private Map<Integer, String> findPrimaryAndSecondaryCostBracket(List<CostTracking> costTrackingList) {
        Map<Integer, String> primaryAndSecondaryCostBracket = new HashMap<>();
        Collections.sort(costTrackingList, new SortCostTracking());
        int count = 1;
        // sort here for secondary cuisines
        for(CostTracking costTracking1 : costTrackingList){
            primaryAndSecondaryCostBracket.put(count , costTracking1.getType());
            count++;
        }
        return primaryAndSecondaryCostBracket;
    }

    private Map<Integer, String> findPrimaryAndSecondaryCuisine(List<CuisineTracking> cuisines) {
        Map<Integer, String> primaryAndSecondaryCuisines = new HashMap<>();
        Collections.sort(cuisines, new SortCuisine());
        int count = 1;
        for(CuisineTracking cuisineTracking1 : cuisines){
            primaryAndSecondaryCuisines.put(count , cuisineTracking1.getType());
            count++;
        }
        return primaryAndSecondaryCuisines;
    }
}
