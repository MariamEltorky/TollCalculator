package com.proj.TollCalculator;

import com.proj.TollCalculator.errors.ParameterNotFoundException;
import com.proj.TollCalculator.models.JPA.TollFee;
import com.proj.TollCalculator.models.JPA.Weekends;
import com.proj.TollCalculator.models.app.Car;
import com.proj.TollCalculator.models.app.Motorbike;
import com.proj.TollCalculator.models.request.RequestDate;
import com.proj.TollCalculator.services.FreeVehicleAndDateServiceImpl;
import com.proj.TollCalculator.services.TollCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TollCalculatorServiceTest {

    private TollCalculatorServiceImpl tollCalculatorService;

    @BeforeAll
    public void setup() {

        Map<Integer, TollFee> tollFeeMap = new HashMap<>();
        tollFeeMap.put(5000, new TollFee(5000, Time.valueOf("06:00:00"), Time.valueOf("06:29:59"), 8));
        tollFeeMap.put(5001, new TollFee(5001, Time.valueOf("06:30:00"), Time.valueOf("06:59:59"), 13));
        tollFeeMap.put(5002, new TollFee(5002, Time.valueOf("07:00:00"), Time.valueOf("07:59:59"), 18));
        tollFeeMap.put(5003, new TollFee(5003, Time.valueOf("08:00:00"), Time.valueOf("08:29:59"), 13));
        tollFeeMap.put(5004, new TollFee(5004, Time.valueOf("08:30:00"), Time.valueOf("14:59:59"), 8));
        tollFeeMap.put(5005, new TollFee(5005, Time.valueOf("15:00:00"), Time.valueOf("15:29:59"), 13));
        tollFeeMap.put(5006, new TollFee(5006, Time.valueOf("15:30:00"), Time.valueOf("16:59:59"), 18));
        tollFeeMap.put(5007, new TollFee(5007, Time.valueOf("17:00:00"), Time.valueOf("17:59:59"), 13));
        tollFeeMap.put(5008, new TollFee(5008, Time.valueOf("18:00:00"), Time.valueOf("18:29:59"), 8));

        Map<Integer, String> freeVehiclesMap = new HashMap<>();
        freeVehiclesMap.put(1, "Motorbike");
        freeVehiclesMap.put(2, "Tractor");
        freeVehiclesMap.put(3, "Emergency");
        freeVehiclesMap.put(4, "Diplomat");
        freeVehiclesMap.put(5, "Foreign");
        freeVehiclesMap.put(6, "Military");

        Map<Integer, LocalDate> holidaysMap = new HashMap<>();
        holidaysMap.put(4000, LocalDate.parse("2013-01-01"));
        holidaysMap.put(4001, LocalDate.parse("2013-03-28"));
        holidaysMap.put(4002, LocalDate.parse("2013-03-29"));
        holidaysMap.put(4003, LocalDate.parse("2013-04-01"));
        holidaysMap.put(4004, LocalDate.parse("2013-04-30"));
        holidaysMap.put(4005, LocalDate.parse("2013-05-01"));
        holidaysMap.put(4006, LocalDate.parse("2013-05-08"));
        holidaysMap.put(4007, LocalDate.parse("2013-05-09"));
        holidaysMap.put(4008, LocalDate.parse("2013-06-05"));
        holidaysMap.put(4009, LocalDate.parse("2013-06-06"));
        holidaysMap.put(4010, LocalDate.parse("2013-06-21"));
        holidaysMap.put(4011, LocalDate.parse("2013-11-01"));
        holidaysMap.put(4012, LocalDate.parse("2013-12-24"));
        holidaysMap.put(4013, LocalDate.parse("2013-12-25"));
        holidaysMap.put(4014, LocalDate.parse("2013-12-26"));
        holidaysMap.put(4015, LocalDate.parse("2013-12-31"));

        Map<String, String> configurationMap= new HashMap<>();
        configurationMap.put("freeYear","2013");
        configurationMap.put("country","Egypt");
        configurationMap.put("freeMonth","7");

        Weekends weekendsObj=new Weekends("Egypt","Friday,Saturday");

        FreeVehicleAndDateServiceImpl freeVehicleAndDateService = new FreeVehicleAndDateServiceImpl(freeVehiclesMap, holidaysMap,configurationMap,weekendsObj);

        this.tollCalculatorService = new TollCalculatorServiceImpl(tollFeeMap, freeVehicleAndDateService);
    }

    @Test
    public void testGetFeePerTime() {
        assertEquals(13, this.tollCalculatorService.getFeePerTime(LocalTime.parse("08:15:49")));
        assertEquals(18, this.tollCalculatorService.getFeePerTime(LocalTime.parse("16:00:49")));
        assertEquals(18, this.tollCalculatorService.getFeePerTime(LocalTime.parse("07:29:49")));
        assertEquals(0, this.tollCalculatorService.getFeePerTime(LocalTime.parse("23:59:00")));

        assertNotEquals(15, this.tollCalculatorService.getFeePerTime(LocalTime.parse("06:29:30")));
        assertNotEquals(16, this.tollCalculatorService.getFeePerTime(LocalTime.parse("10:32:00")));
        assertNotEquals(18, this.tollCalculatorService.getFeePerTime(LocalTime.parse("04:29:30")));
        assertNotEquals(13, this.tollCalculatorService.getFeePerTime(LocalTime.parse("12:35:00")));
    }

    @Test
    public void testCalculateTollFees() {

        List<Time> localTimes1 = new ArrayList<>();
        localTimes1.add(Time.valueOf("06:29:30"));
        localTimes1.add(Time.valueOf("06:59:30"));
        localTimes1.add(Time.valueOf("07:59:35"));
        localTimes1.add(Time.valueOf("08:59:20"));

        int fee1 = this.tollCalculatorService.calculateTollFees(localTimes1);
        assertEquals(31, fee1);

        List<Time> localTimes2 = new ArrayList<>();
        localTimes2.add(Time.valueOf("06:29:30"));
        localTimes2.add(Time.valueOf("06:59:30"));
        localTimes2.add(Time.valueOf("07:59:35"));
        localTimes2.add(Time.valueOf("08:59:20"));
        localTimes2.add(Time.valueOf("09:31:00"));
        localTimes2.add(Time.valueOf("10:32:00"));

        int fee2 = this.tollCalculatorService.calculateTollFees(localTimes2);
        assertEquals(47, fee2);

        List<Time> localTimes3 = new ArrayList<>();
        localTimes3.add(Time.valueOf("08:15:49"));
        localTimes3.add(Time.valueOf("08:31:49"));
        localTimes3.add(Time.valueOf("16:00:49"));
        localTimes3.add(Time.valueOf("07:29:49"));

        int fee3 = this.tollCalculatorService.calculateTollFees(localTimes3);
        assertNotEquals(50, fee3);
    }

    @Test
    public void testGetTollFeeRequestDateIsNull() {
        ParameterNotFoundException parameterNotFoundException = assertThrows(ParameterNotFoundException.class, () -> {
            this.tollCalculatorService.getTotalTollFee(new Car(), null);
        });
        assertEquals("requestDate Parameter is null", parameterNotFoundException.getMessage());
    }

    @Test
    public void testGetTotalTollFee1() {

        List<Time> timeList1 = new ArrayList<>();
        timeList1.add(Time.valueOf("08:15:49"));
        timeList1.add(Time.valueOf("08:31:49"));
        timeList1.add(Time.valueOf("16:00:49"));
        timeList1.add(Time.valueOf("07:29:49"));

        RequestDate requestDate1 = new RequestDate(Date.valueOf("2013-08-27"), timeList1);

        int totalFee1 = this.tollCalculatorService.getTotalTollFee(new Car(), requestDate1);

        assertEquals(44, totalFee1);
    }

    @Test
    public void testGetTotalTollFee2() {

        List<Time> timeList2 = new ArrayList<>();
        timeList2.add(Time.valueOf("06:29:00"));
        timeList2.add(Time.valueOf("18:15:00"));
        timeList2.add(Time.valueOf("14:30:00"));
        timeList2.add(Time.valueOf("13:58:00"));
        timeList2.add(Time.valueOf("12:35:00"));
        timeList2.add(Time.valueOf("10:00:00"));
        timeList2.add(Time.valueOf("07:30:00"));
        timeList2.add(Time.valueOf("23:59:00"));

        RequestDate requestDate2 = new RequestDate(Date.valueOf("2013-12-06"), timeList2);

        int totalFee2 = this.tollCalculatorService.getTotalTollFee(new Motorbike(), requestDate2);

        assertEquals(0, totalFee2);
    }

    @Test
    public void testGetTotalTollFee3() {

        List<Time> timeList3 = new ArrayList<>();
        timeList3.add(Time.valueOf("06:29:00"));
        timeList3.add(Time.valueOf("18:15:00"));
        timeList3.add(Time.valueOf("14:30:00"));
        timeList3.add(Time.valueOf("13:58:00"));
        timeList3.add(Time.valueOf("12:35:00"));
        timeList3.add(Time.valueOf("10:00:00"));
        timeList3.add(Time.valueOf("07:30:00"));
        timeList3.add(Time.valueOf("23:59:00"));

        RequestDate requestDate3 = new RequestDate(Date.valueOf("2013-12-08"), timeList3);

        int totalFee3 = this.tollCalculatorService.getTotalTollFee(new Car(), requestDate3);

        assertEquals(58, totalFee3);
    }

    @Test
    public void testGetTotalTollFeeGreaterThan60() {

        List<Time> timeList = new ArrayList<>();
        timeList.add(Time.valueOf("06:29:00"));
        timeList.add(Time.valueOf("18:15:00"));
        timeList.add(Time.valueOf("14:30:00"));
        timeList.add(Time.valueOf("13:58:00"));
        timeList.add(Time.valueOf("12:35:00"));
        timeList.add(Time.valueOf("10:00:00"));
        timeList.add(Time.valueOf("07:30:00"));
        timeList.add(Time.valueOf("07:40:00"));
        timeList.add(Time.valueOf("07:50:00"));
        timeList.add(Time.valueOf("23:59:00"));

        RequestDate requestDate = new RequestDate(Date.valueOf("2013-12-08"), timeList);

        int totalFee = this.tollCalculatorService.getTotalTollFee(new Car(), requestDate);

        assertEquals(60, totalFee);
    }
}
