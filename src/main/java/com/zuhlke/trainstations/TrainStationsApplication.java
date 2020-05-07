package com.zuhlke.trainstations;

import com.zuhlke.trainstations.service.TrainStationDBService;
import com.zuhlke.trainstations.service.TrainStationAPIService;
import com.zuhlke.trainstations.model.Places;
import com.zuhlke.trainstations.util.APIHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class TrainStationsApplication {

	@Autowired
	TrainStationDBService trainStationDBService;

	public static void main(String[] args) {
		SpringApplication.run(TrainStationsApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public int run(RestTemplateBuilder restTemplate) {
		Places query = new TrainStationAPIService(restTemplate).getStation("london", "tube_station");
		trainStationDBService.saveStationDetails(query.getStationDetails());
		return 0;
	}
}