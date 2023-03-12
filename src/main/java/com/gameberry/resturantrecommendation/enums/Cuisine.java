package com.gameberry.resturantrecommendation.enums;

public enum Cuisine {

    SOUTH_INDIAN("1", "SouthIndian"),
    NORTH_INDIAN("2", "NorthIndian"),
    CHINESE("3", "Chinese");

    Cuisine(String value, String name){
        this.name = name;
        this.value = value;
    }
    private String value;
    private String name;

    public String getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public String getValue(String name){
        switch (name){
            case "SouthIndian":
                return "1";
            case "NorthIndian":
                return "2";
            case "Chinese":
                return "3";
            default:
                return "";
        }
    }
}
