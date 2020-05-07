package com.zuhlke.trainstations.service;

import com.zuhlke.trainstations.model.Places;
import com.zuhlke.trainstations.util.APIHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class TrainStationAPIService {

    APIHandler apiHandler = new APIHandler();
    String[] keys = apiHandler.getKey();
    String APP_KEY = keys.length==2 ? keys[0] : "";
    String APP_ID = keys.length==2 ? keys[1] : "";
    public final String BASE_URL = "https://transportapi.com/v3/uk/places.json?app_id=" + APP_ID + "&app_key=" + APP_KEY;

    @Autowired
    RestTemplate restTemplate;

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
        Places stations = restTemplate.getForObject(requestUri, Places.class, urlParameters);
        return stations;
    }
}
