package com.gameberry.resturantrecommendation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtil {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException io) {
            log.error("exception while converting object to string", io);
            return null;
        }
    }
}
