package com.zuhlke.trainstations.service;

import com.zuhlke.trainstations.model.StationDetails;
import com.zuhlke.trainstations.repository.StationDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class) //to run with JUnit 5 (for parameterized tests)
public class TrainStationsDBTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public TrainStationDBService trainStationDBService() {
            return new TrainStationDBService();
        }
    }

    @Autowired
    private TrainStationDBService trainStationService;

    @MockBean
    StationDetailsRepository detailsRepository;

    String stationName = "Some station";
    StationDetails station1 = new StationDetails("", "Wembley", 0.0, 0.0, 0, "", "WMBY");
    StationDetails station2 = new StationDetails("", stationName, 0.0, 0.0, 0, "", "WddMBY");

    @ParameterizedTest
    @ValueSource(longs = {1, 3, 5, 15, Long.MAX_VALUE})
    public void canGetSumOfStations(long number) {
        Mockito.when(detailsRepository.count()).thenReturn(number);
        Long sum = trainStationService.getStationDetailsSum();
        assertThat(sum).isEqualTo(number);
    }

    @Test
    public void canSaveAStation() {
        //in this test we use mocking instead of testConfig because we can only test whether method is invoked
        //correctly. It is a void method so not much can be tested here
        List<StationDetails> stationDetailsList1 = Arrays.asList(station1);
        TrainStationDBService trainStationDBService = mock(TrainStationDBService.class);
        trainStationDBService.saveStationDetails(stationDetailsList1);
        verify(trainStationDBService, only()).saveStationDetails(stationDetailsList1);
    }

    @Test
    public void canGetAllStationDetails() {
        List<StationDetails> stationDetailsList1 = Arrays.asList(station1, station2);
        Mockito.when(detailsRepository.findAll()).thenReturn(stationDetailsList1);
        List<StationDetails> allStations = trainStationService.getAllStationDetails();
        assertEquals(allStations.size(), 2);
    }

    @Test
    public void canGetStationDetailsByCode() {
        List<StationDetails> stationDetailsList2 = Arrays.asList(station2);
        Mockito.when(detailsRepository.findByAtcocode(station2.getAtcocode())).thenReturn(stationDetailsList2);
        List<StationDetails> selectedStations = trainStationService.getStationDetailsByCode(station2.getAtcocode());
        assertEquals(selectedStations.size(), 1);
        assertEquals(station2.getName(), stationName);
    }
}
