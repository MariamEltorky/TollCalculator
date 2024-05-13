package com.proj.TollCalculator;

import com.proj.TollCalculator.models.request.Content;
import com.proj.TollCalculator.models.request.RequestDate;
import com.proj.TollCalculator.models.request.TollCalculatorRequestBody;
import com.proj.TollCalculator.models.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TollCalculatorControllerTest {
    static Logger logger = LogManager.getLogger(TollCalculatorControllerTest.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPostAPI_success() throws Exception {
        List<Time> times = new ArrayList<>();

        times.add(Time.valueOf("08:15:49"));
        times.add(Time.valueOf("08:31:49"));
        times.add(Time.valueOf("16:00:49"));
        times.add(Time.valueOf("07:29:49"));

        RequestDate requestDate = new RequestDate(Date.valueOf("2013-08-28"), times);
        Content content = new Content("Car", requestDate);


        TollCalculatorRequestBody tollCalculatorRequestBody = TollCalculatorRequestBody
                .builder()
                .content(content)
                .build();

        com.proj.TollCalculator.models.response.Content contentResponse =
                new com.proj.TollCalculator.models.response.Content(44);

        ResponseBody expectedResponseBody = ResponseBody
                .builder()
                .content(contentResponse)
                .build();

        final String baseUrl = "http://localhost:" + 8080 + "/calculate/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TollCalculatorRequestBody> request = new HttpEntity<>(tollCalculatorRequestBody, headers);

        ResponseEntity<ResponseBody> response = this.restTemplate.postForEntity(uri, request, ResponseBody.class);

        logger.info("TollCalculatorControllerTest - testPostAPI_success - response - {}", response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody().toString()).isEqualTo(expectedResponseBody.toString());

    }

}
