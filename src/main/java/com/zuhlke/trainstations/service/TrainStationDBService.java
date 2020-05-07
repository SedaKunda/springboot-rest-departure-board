package com.zuhlke.trainstations.service;

import com.zuhlke.trainstations.model.StationDetails;
import com.zuhlke.trainstations.repository.StationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainStationDBService {

    @Autowired
    StationDetailsRepository detailsRepository;

    public List<StationDetails> getStationDetailsByCode(String name) {
        return detailsRepository.findByAtcocode(name);
    }

    public List<StationDetails> getAllStationDetails() {
        List<StationDetails> stationList = new ArrayList<>();
        for (StationDetails s : detailsRepository.findAll()) {
            stationList.add(s);
        }
        return stationList;
    }

    public void saveStationDetails(List<StationDetails> stations) {
        detailsRepository.saveAll(stations);
    }

    public Long getStationDetailsSum() {
        return detailsRepository.count();
    }
}
