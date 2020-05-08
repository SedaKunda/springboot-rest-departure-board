package com.zuhlke.trainstations.controller;

import com.zuhlke.trainstations.model.StationDetails;
import com.zuhlke.trainstations.service.TrainStationDBService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrainStationController.class)
@WebMvcTest(TrainStationController.class)
class TrainStationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrainStationDBService service;
    String code = "WMBY";
    StationDetails station1 = new StationDetails("", "Wembley", 0.0, 0.0, 0, "", code);

    @Test
    void canGetStationByCode() throws Exception {
        List<StationDetails> allStations = Arrays.asList(station1);
        given(service.getStationDetailsByCode(code)).willReturn(allStations);
        mvc.perform(get("/station/" + code))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(station1.getName())));
        verify(service, VerificationModeFactory.times(1)).getStationDetailsByCode(code);
    }

    @Test
    void canGetAllStationDetails() throws Exception {
        List<StationDetails> allStations = Arrays.asList(station1, station1);
        given(service.getAllStationDetails()).willReturn(allStations);
        mvc.perform(get("/station/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(station1.getName())));
        verify(service, VerificationModeFactory.times(1)).getAllStationDetails();
    }

    @Test
    void canGetDetailsSum() throws Exception {
        given(service.getStationDetailsSum()).willReturn(Long.valueOf(3));
        mvc.perform(get("/count"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(3)));
        verify(service, VerificationModeFactory.times(1)).getStationDetailsSum();
    }
}