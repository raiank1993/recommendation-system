package com.gameberry.resturantrecommendation.sort;

import com.gameberry.resturantrecommendation.requests.CostTracking;
import com.gameberry.resturantrecommendation.requests.CuisineTracking;

import java.util.Comparator;

public class SortCostTracking implements Comparator<CostTracking> {

    @Override
    public int compare(CostTracking o1, CostTracking o2) {
        return (Integer.parseInt(o2.getNoOfOrders()) - Integer.parseInt(o1.getNoOfOrders()));
    }
}
