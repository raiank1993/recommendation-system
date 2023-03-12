package com.gameberry.resturantrecommendation.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameberry.resturantrecommendation.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class RecommendationRequest {

    private User user;
    private Restaurant[] availableRestaurants;

    @Override
    public String toString() {
        return JsonUtil.getString(this);
    }
}
