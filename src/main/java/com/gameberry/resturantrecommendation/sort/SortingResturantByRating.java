package com.gameberry.resturantrecommendation.sort;

import com.gameberry.resturantrecommendation.requests.Restaurant;

import java.util.Comparator;

public class SortingResturantByRating implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        return Float.compare(o2.getRating(), o1.getRating());
    }
}
