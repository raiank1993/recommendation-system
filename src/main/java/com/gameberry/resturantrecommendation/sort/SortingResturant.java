package com.gameberry.resturantrecommendation.sort;

import com.gameberry.resturantrecommendation.requests.Restaurant;

import java.util.Comparator;

public class SortingResturant implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant a, Restaurant b) {
        return b.getOnboardedTime().compareTo(a.getOnboardedTime());
    }
}
