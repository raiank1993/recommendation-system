package com.gameberry.resturantrecommendation.controllers;

import com.gameberry.resturantrecommendation.requests.RecommendationRequest;
import com.gameberry.resturantrecommendation.requests.Restaurant;
import com.gameberry.resturantrecommendation.requests.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@Slf4j
public class ResturantController {

    @Produces("application/json")
    @RequestMapping(value = "user/recommendation", method = RequestMethod.POST)
    public void getUserResturantRecommendation(@RequestBody RecommendationRequest recommendationRequest) {
        log.info("request sms webhook {}",recommendationRequest.toString());
        return;
    }
}
