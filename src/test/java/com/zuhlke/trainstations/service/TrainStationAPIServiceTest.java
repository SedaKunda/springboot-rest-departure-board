package com.zuhlke.trainstations.service;

import com.zuhlke.trainstations.model.Places;
import com.zuhlke.trainstations.util.APIHandler;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrainStationAPIService.class)
@RestClientTest(TrainStationAPIService.class)
class TrainStationAPIServiceTest {

    @Autowired
    private TrainStationAPIService service;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void testGetStationInService() {
        String BASE_URL = new APIHandler().getBASE_URL();
        String TEST_URL = BASE_URL + "&query=london&type=tube_station";
        String place = "{\"request_time\": \"sometime\",\"source\": \"ABC\", \"acknowledgements\": \"test\",\"member\": []}";
        this.server.expect(requestTo(TEST_URL))
                .andExpect(method(GET))
                .andRespond(withSuccess(place, MediaType.APPLICATION_JSON));
        Places details = this.service.getStation("london", "tube_station");
        assertNotNull(details);
        assertThat(details.getSource()).isEqualTo("ABC");
    }
}