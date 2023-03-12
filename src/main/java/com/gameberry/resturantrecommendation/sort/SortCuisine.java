package com.gameberry.resturantrecommendation.sort;

import com.gameberry.resturantrecommendation.requests.CuisineTracking;

import java.util.Comparator;

public class SortCuisine implements Comparator<CuisineTracking> {

    @Override
    public int compare(CuisineTracking o1, CuisineTracking o2) {
        return (Integer.parseInt(o2.getNoOfOrders()) - Integer.parseInt(o1.getNoOfOrders()));
    }
}
