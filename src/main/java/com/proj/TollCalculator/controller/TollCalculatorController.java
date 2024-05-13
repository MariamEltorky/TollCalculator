package com.proj.TollCalculator.controller;

import com.proj.TollCalculator.models.app.Car;
import com.proj.TollCalculator.models.app.Motorbike;
import com.proj.TollCalculator.models.app.OtherVehicle;
import com.proj.TollCalculator.models.app.Vehicle;
import com.proj.TollCalculator.models.request.RequestDate;
import com.proj.TollCalculator.models.request.TollCalculatorRequestBody;
import com.proj.TollCalculator.models.response.Content;
import com.proj.TollCalculator.models.response.ResponseBody;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*****************************************************************

 Rest Controller to take request, process it and return response.

 *****************************************************************/
@RestController
@AllArgsConstructor
public class TollCalculatorController {
    static Logger logger = LogManager.getLogger(TollCalculatorController.class);
    private final com.proj.TollCalculator.services.TollCalculatorService TollCalculatorService;


    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseBody> requestBodyHandler(@Valid @RequestBody TollCalculatorRequestBody tollCalculatorRequestBody) {
        logger.info("TollCalculatorController - Start executing requestBodyHandler");

        logger.info("TollCalculatorController - requestBodyHandler - Toll Calculator Request Body - {}", tollCalculatorRequestBody.toString());

        int totalFee = -1;
        Vehicle vehicle = null;

        String vehicleType = tollCalculatorRequestBody.getContent().getVehicleType() != "" ?
                tollCalculatorRequestBody.getContent().getVehicleType() : "";

        if (vehicleType == "") {
            logger.info("TollCalculatorController - requestBodyHandler - vehicleType is null");
            totalFee = -1;
        } else {
            if (vehicleType.equals("Car")) {
                vehicle = new Car();
            } else if (vehicleType.equals("Motorbike")) {
                vehicle = new Motorbike();
            } else {
                vehicle = new OtherVehicle(vehicleType);
            }
            RequestDate requestDate = tollCalculatorRequestBody.getContent().getDates();
            totalFee = TollCalculatorService.getTotalTollFee(vehicle, requestDate);
            logger.info("TollCalculatorController - requestBodyHandler - totalFee - {}", totalFee);
        }
        Content content = new Content(totalFee);
        ResponseBody responseBody = ResponseBody
                .builder()
                .content(content)
                .build();


        logger.info("TollCalculatorController - requestBodyHandler - Toll Calculator Response Body - {}", responseBody.toString());

        logger.info("TollCalculatorController - End executing requestBodyHandler");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
