package com.zuhlke.trainstations.repository;

import com.zuhlke.trainstations.TrainStationsApplication;
import com.zuhlke.trainstations.model.StationDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrainStationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StationDetailsRepositoryTest { //integration test

    @Autowired
    private StationDetailsRepository repository;

    String stationName = "Some station";
    StationDetails station1 = new StationDetails("", "Wembley", 0.0, 0.0, 0, "", "WMBY");
    StationDetails station2 = new StationDetails("", stationName, 0.0, 0.0, 0, "", "WddMBY");

    @BeforeEach
    void setUp() {
    }

    @Test
    void canFindByAtcocode() {
        repository.save(station1);
        repository.save(station2);
        List<StationDetails> stationDetails = repository.findByAtcocode("WddMBY");
        assertThat(stationDetails, notNullValue());
        assertThat(stationDetails.size(), is(1));
        assertThat(stationDetails.get(0).getName(), notNullValue());
        assertThat(stationDetails.get(0).getName(), is(stationName));
    }
}