package com.zuhlke.trainstations.service;

import com.zuhlke.trainstations.model.Places;
import com.zuhlke.trainstations.util.APIHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class TrainStationAPIService {

    private String BASE_URL = new APIHandler().getBASE_URL();
    private final RestTemplate restTemplate;

    public TrainStationAPIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }

    public Places getStation(String query, String type) {
        String requestUri = BASE_URL + "&query={query}&type={type}";
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("query", query);
        urlParameters.put("type", type);
        return restTemplate.getForObject(requestUri, Places.class, urlParameters);
    }
}
