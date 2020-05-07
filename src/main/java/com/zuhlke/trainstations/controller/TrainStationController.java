package com.zuhlke.trainstations.controller;

import com.zuhlke.trainstations.model.StationDetails;
import com.zuhlke.trainstations.service.TrainStationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainStationController {

    @Autowired
    TrainStationDBService service;

    @GetMapping("/station/{atcocode}")
    public List<StationDetails> getStationByCode(@PathVariable String atcocode) {
        return service.getStationDetailsByCode(atcocode);
    }

    @GetMapping("/station/all")
    public List<StationDetails> getAllStationDetails() {
        return service.getAllStationDetails();
    }

    @GetMapping("/count")
    public Long getDetailsSum() {
        return service.getStationDetailsSum();
    }
}
