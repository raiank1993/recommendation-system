package com.gameberry.resturantrecommendation.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameberry.resturantrecommendation.enums.Cuisine;
import com.gameberry.resturantrecommendation.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Restaurant {

    private String restaurantId;
    private String cuisine;
    private int costBracket;
    private float rating;
    private boolean isRecommended;
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date onboardedTime;

    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }
}
