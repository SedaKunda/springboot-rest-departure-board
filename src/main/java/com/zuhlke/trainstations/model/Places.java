package com.zuhlke.trainstations.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Places {
    
    private Long id;
    private String request_time;
    private String source;
    private String acknowledgements;
    @JsonProperty(value = "member")
    private List<StationDetails> stationDetails;

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAcknowledgements() {
        return acknowledgements;
    }

    public void setAcknowledgements(String acknowledgements) {
        this.acknowledgements = acknowledgements;
    }

    public List<StationDetails> getStationDetails() {
        return stationDetails;
    }

    public void setStationDetails(List<StationDetails> stationDetails) {
        this.stationDetails = stationDetails;
    }
}