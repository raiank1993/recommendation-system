package com.gameberry.resturantrecommendation.controllers;

import com.gameberry.resturantrecommendation.requests.RecommendationRequest;
import com.gameberry.resturantrecommendation.services.ResturantRecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.List;

@RestController
@Slf4j
public class ResturantController {

    private final ResturantRecommendationService resturantRecommendationService;

    @Autowired
    public ResturantController(ResturantRecommendationService resturantRecommendationService) {
        this.resturantRecommendationService = resturantRecommendationService;
    }

    @Produces("application/json")
    @RequestMapping(value = "user/recommendation", method = RequestMethod.POST)
    public ResponseEntity<?> getUserResturantRecommendation(@RequestBody RecommendationRequest recommendationRequest) {
        log.info("request recommendation {}",recommendationRequest.toString());
        return new ResponseEntity<List<String>>(resturantRecommendationService.getUserResturantRecommendation(recommendationRequest), HttpStatus.CREATED);
    }
}
