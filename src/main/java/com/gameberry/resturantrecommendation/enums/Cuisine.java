package com.gameberry.resturantrecommendation.enums;

public enum Cuisine {

    SOUTH_INDIAN(1, "SouthIndian"),
    NORTH_INDIAN(2, "NorthIndian"),
    CHINESE(3, "Chinese");

    Cuisine(Integer value, String name){
        this.name = name;
        this.value = value;
    }
    private int value;
    private String name;

    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }
}
