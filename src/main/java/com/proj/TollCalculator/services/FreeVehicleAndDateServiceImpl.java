package com.proj.TollCalculator.services;

import com.proj.TollCalculator.models.JPA.Weekends;
import com.proj.TollCalculator.models.app.Vehicle;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

@Service
@AllArgsConstructor
public class FreeVehicleAndDateServiceImpl implements FreeVehicleAndDateService {

    static Logger logger = LogManager.getLogger(FreeVehicleAndDateServiceImpl.class);
    private final Map<Integer, String> freeVehiclesMap;
    private final Map<Integer, LocalDate> holidaysMap;
    private final Map<String, String> configureMap;

    private final Weekends weekendsObj;

    @Override
    public boolean isTollFreeVehicle(Vehicle vehicleType) {
        logger.info("FreeVehicleAndDateImpl - Start executing isTollFreeVehicle");

        logger.info("FreeVehicleAndDateImpl - isTollFreeVehicle - freeVehiclesMap - {}", freeVehiclesMap);

        logger.info("FreeVehicleAndDateImpl - isTollFreeVehicle - vehicleType - {}", vehicleType.getType());

        boolean isFreeVehicle = freeVehiclesMap != null && vehicleType.getType() != "" &&
                freeVehiclesMap.containsValue(vehicleType.getType()) ? true : false;

        logger.info("FreeVehicleAndDateImpl - isTollFreeVehicle - isFreeVehicle - {}", isFreeVehicle);

        logger.info("FreeVehicleAndDateImpl - End executing isTollFreeVehicle");

        return isFreeVehicle;
    }

    @Override
    public boolean isTollFreeDate(Date date) {

        return isWeekend(date) || isHoliday(date);
    }

    private boolean isHoliday(Date date) {
        logger.info("FreeVehicleAndDateImpl - Start executing isHoliday");

        logger.info("FreeVehicleAndDateImpl - isHoliday - holidaysMap - {}", holidaysMap);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        logger.info("FreeVehicleAndDateImpl - isHoliday - year - {}", year);

        int month = calendar.get(Calendar.MONTH);
        logger.info("FreeVehicleAndDateImpl - isHoliday - Month - {}", month + 1);

        LocalDate localDate = date.toLocalDate();
        logger.info("FreeVehicleAndDateImpl - isHoliday - Date - {}", localDate);

        logger.info("FreeVehicleAndDateImpl - isHoliday - contains - {}", holidaysMap.containsValue(localDate));
        boolean holidayFlag = false;

        int freeMonth= Integer.parseInt(configureMap.get("freeMonth"));
        logger.info("FreeVehicleAndDateImpl - isHoliday - freeMonth - {}", freeMonth);

        int freeYear= Integer.parseInt(configureMap.get("freeYear"));
        logger.info("FreeVehicleAndDateImpl - isHoliday - freeYear - {}", freeYear);

        if (holidaysMap != null && localDate != null && holidaysMap.containsValue(localDate)) {
            logger.info("FreeVehicleAndDateImpl - isHoliday - FREE, It's a HOLIDAY ");
            holidayFlag = true;
        } else if (localDate != null && month + 1 == freeMonth &&  year == freeYear) {
            logger.info("FreeVehicleAndDateImpl - isHoliday - FREE, It's a Free Month");
            holidayFlag = true;
        }else {
            holidayFlag = false;
        }

        logger.info("FreeVehicleAndDateImpl - isHoliday - holidayFlag - {}", holidayFlag);

        logger.info("FreeVehicleAndDateImpl - End executing isHoliday");
        return holidayFlag;
    }

    private boolean isWeekend(Date date) {
        logger.info("FreeVehicleAndDateImpl - Start executing isWeekend");

        String currentCountry = configureMap.get("country");
        logger.info("FreeVehicleAndDateImpl - isWeekend - currentCountry - {}", currentCountry);

//        Weekends weekendsObj = weekendsRepo.findWeekendsByCountry(currentCountry);
        String weekends = weekendsObj.getWeekends();
        logger.info("FreeVehicleAndDateImpl - isWeekend - weekends - {}", weekends);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        logger.info("FreeVehicleAndDateImpl - isWeekend - dayOfWeek - {}", dayOfWeek);
        boolean isDateOnWeekend=false;
        if (weekends.equalsIgnoreCase("Saturday,Sunday")) {
            isDateOnWeekend = dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY;
        } else if (weekends.equalsIgnoreCase("Thursday,Friday")) {
            isDateOnWeekend = dayOfWeek == Calendar.THURSDAY || dayOfWeek == Calendar.FRIDAY;
        } else if (weekends.equalsIgnoreCase("Friday,Saturday")) {
            isDateOnWeekend = dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY;
        }else {
            isDateOnWeekend=false;
        }

        logger.info("FreeVehicleAndDateImpl - isWeekend - isDateOnWeekend - {}", isDateOnWeekend);

        logger.info("FreeVehicleAndDateImpl - End executing isWeekend");
        return isDateOnWeekend;
    }
}
