package com.gameberry.resturantrecommendation.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameberry.resturantrecommendation.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private CuisineTracking[]  cuisines;
    private CostTracking[] costBracket;

    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }
}
