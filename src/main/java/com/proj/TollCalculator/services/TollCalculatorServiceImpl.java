package com.proj.TollCalculator.services;

import com.proj.TollCalculator.errors.ParameterNotFoundException;
import com.proj.TollCalculator.models.JPA.TollFee;
import com.proj.TollCalculator.models.app.Vehicle;
import com.proj.TollCalculator.models.request.RequestDate;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

/*****************************************************************

 To make all operations of the app:
    1-Check if VehicleType is free or not
    2-Check if Date is free or not
    3-Calculate Toll Fee per time
    4-Calculate Total Toll Fee for all times

 *****************************************************************/

@Service
@AllArgsConstructor
public class TollCalculatorServiceImpl implements TollCalculatorService {

    static Logger logger = LogManager.getLogger(TollCalculatorServiceImpl.class);
    private final Map<Integer, TollFee> tollFeeMap;
    private final FreeVehicleAndDateServiceImpl freeVehicleDay;

    // Make important checks and send list of times to calculateTollFees method to calculate total fee
    public int getTotalTollFee(Vehicle vehicle, RequestDate requestDate) {

        logger.info("TollCalculatorServiceImpl - Start executing getTollFee");
        int totalFee = 0;
        logger.info("TollCalculatorServiceImpl - getTollFee - Vehicle Type - {}", vehicle.getType());
        logger.info("TollCalculatorServiceImpl - getTollFee - Request Date - {}", requestDate);

        if (requestDate == null) {
            throw new ParameterNotFoundException("requestDate Parameter is null");
        }

        boolean isFreeVehicle = freeVehicleDay.isTollFreeVehicle(vehicle);
        logger.info("TollCalculatorServiceImpl - getTollFee - isFreeVehicle - {}", isFreeVehicle);
        if (isFreeVehicle) {
            logger.info("TollCalculatorServiceImpl - getTollFee - FREE, It's a Free Vehicle");
            return 0;
        } else {
            logger.info("TollCalculatorServiceImpl - getTollFee - Date - {}", requestDate.getDate().toLocalDate());
            boolean isFreeDate = freeVehicleDay.isTollFreeDate(requestDate.getDate());
            logger.info("TollCalculatorServiceImpl - getTollFee - isFreeDate - {}", isFreeDate);
            if (isFreeDate) {
                logger.info("TollCalculatorServiceImpl - getTollFee - FREE, It's a Free Date");
                return 0;
            } else {
                totalFee = calculateTollFees(requestDate.getTimes());
                logger.info("TollCalculatorServiceImpl - getTollFee - totalFee - {}", totalFee);

            }
        }

        logger.info("TollCalculatorServiceImpl - End executing getTollFee");
        return totalFee;
    }

    // Take list of times and sort them and use getFeePerTime method to get fee per that time
    public int calculateTollFees(List<Time> localTimes) {
        logger.info("TollCalculatorServiceImpl - Start executing calculateTollFees");
        List<LocalTime> localTimesList = localTimes.stream().
                map(time -> time.toLocalTime()).collect(Collectors.toList());

        Collections.sort(localTimesList);
        logger.info("TollCalculatorServiceImpl - calculateTollFees - sortedTimeFeeMap - {}", localTimesList);

        LocalTime intervalStart = localTimesList.get(0);
        logger.info("TollCalculatorServiceImpl - calculateTollFees - intervalStart - {}", intervalStart);

        int tempFee = this.getFeePerTime(intervalStart);
        int totalFee = 0;
        for (LocalTime localTime : localTimesList) {
            int nextFee = this.getFeePerTime(localTime);
            long minutes = MINUTES.between(intervalStart, localTime);
            if (minutes <= 60) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            } else {
                intervalStart = localTime;
                logger.info("TollCalculatorServiceImpl - calculateTollFees - intervalStart - {}", intervalStart);
                totalFee += nextFee;
            }
        }
        if (totalFee > 60) totalFee = 60;

        logger.info("TollCalculatorServiceImpl - calculateTollFees - totalFee - {}", totalFee);

        logger.info("TollCalculatorServiceImpl - End executing calculateTollFees");
        return totalFee;
    }

    // Take only one time and access bean of tollFee to check the time is within any range and get fee of it
    public int getFeePerTime(LocalTime localTime) {
        logger.info("TollCalculatorServiceImpl - Start executing getFeePerTime");
        int fee = 0;
        for (Integer tollFeeId : tollFeeMap.keySet()) {
            TollFee tollFeeObj = tollFeeMap.get(tollFeeId);
            LocalTime startTime = tollFeeObj.getStartTime().toLocalTime();
            LocalTime endTime = tollFeeObj.getEndTime().toLocalTime();

            if (!(localTime.isBefore(startTime) || localTime.isAfter(endTime))) {
                fee = tollFeeObj.getFee();
                logger.info("TollCalculatorServiceImpl - getFeePerTime - localTime - {} - startTime - {} - endTime - {} - fee - {}",
                        localTime, startTime, endTime, fee);
                logger.info("TollCalculatorServiceImpl - getFeePerTime - fee - {}", fee);
            }
        }
        if (fee == 0) {
            logger.info("TollCalculatorServiceImpl - getFeePerTime - Free fee - {}", fee);
        }
        logger.info("TollCalculatorServiceImpl - End executing getFeePerTime");
        return fee;
    }


}
