package com.proj.TollCalculator;

import com.proj.TollCalculator.models.JPA.Holidays;
import com.proj.TollCalculator.models.JPA.TollFee;
import com.proj.TollCalculator.models.JPA.Vehicles;
import com.proj.TollCalculator.repositories.HolidaysRepo;
import com.proj.TollCalculator.repositories.TollFeeRepo;
import com.proj.TollCalculator.repositories.VehiclesRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoriesTest {
    static Logger logger = LogManager.getLogger(RepositoriesTest.class);
    @Autowired
    private VehiclesRepo vehiclesRepo;
    @Autowired
    private HolidaysRepo holidaysRepo;
    @Autowired
    private TollFeeRepo tollFeeRepo;

    @Test
    public void findAllFreeVehicalsList() {
        List<Vehicles> freeVehiclesList = this.vehiclesRepo.findAll();
        logger.info("RepositoriesTest - findAllFreeVehicalsList - freeVehiclesList Size - {}", freeVehiclesList.size());
        assertEquals(6, freeVehiclesList.size());
        assertEquals("Motorbike", freeVehiclesList.get(0).getVehicleType());
    }

    @Test
    public void findAllHolidaysList() {
        List<Holidays> holidaysList = this.holidaysRepo.findAll();
        logger.info("RepositoriesTest - findAllHolidaysList - holidaysList Size - {}", holidaysList.size());
        assertEquals(16, holidaysList.size());
        assertEquals(LocalDate.parse("2013-04-01"), holidaysList.get(3).getDayMonth());
    }

    @Test
    public void findAllTollFeeList() {
        List<TollFee> tollFeeList = this.tollFeeRepo.findAll();
        logger.info("RepositoriesTest - findAllTollFeeList - tollFeeList Size - {}", tollFeeList.size());
        assertEquals(9, tollFeeList.size());
        assertEquals(Time.valueOf("08:30:00"), tollFeeList.get(4).getStartTime());
        assertEquals(Time.valueOf("14:59:59"), tollFeeList.get(4).getEndTime());
        assertEquals(8, tollFeeList.get(4).getFee());
    }
}
