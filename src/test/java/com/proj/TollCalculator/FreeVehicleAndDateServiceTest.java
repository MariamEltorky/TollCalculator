package com.proj.TollCalculator;

import com.proj.TollCalculator.models.JPA.Weekends;
import com.proj.TollCalculator.models.app.Car;
import com.proj.TollCalculator.models.app.Motorbike;
import com.proj.TollCalculator.models.app.OtherVehicle;
import com.proj.TollCalculator.models.app.Vehicle;
import com.proj.TollCalculator.services.FreeVehicleAndDateServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FreeVehicleAndDateServiceTest {

    private FreeVehicleAndDateServiceImpl freeVehicleAndDateService;


    public FreeVehicleAndDateServiceTest() {
        Map<Integer, String> freeVehiclesMap = new HashMap<>();
        freeVehiclesMap.put(1,"Motorbike");
        freeVehiclesMap.put(2,"Tractor");
        freeVehiclesMap.put(3,"Emergency");
        freeVehiclesMap.put(4,"Diplomat");
        freeVehiclesMap.put(5,"Foreign");
        freeVehiclesMap.put(6,"Military");

        Map<Integer, LocalDate> holidaysMap = new HashMap<>();
        holidaysMap.put(4000, LocalDate.parse("2013-01-01"));
        holidaysMap.put(4001,LocalDate.parse("2013-03-28"));
        holidaysMap.put(4002,LocalDate.parse("2013-03-29"));
        holidaysMap.put(4003,LocalDate.parse("2013-04-01"));
        holidaysMap.put(4004,LocalDate.parse("2013-04-30"));
        holidaysMap.put(4005,LocalDate.parse("2013-05-01"));
        holidaysMap.put(4006,LocalDate.parse("2013-05-08"));
        holidaysMap.put(4007,LocalDate.parse("2013-05-09"));
        holidaysMap.put(4008,LocalDate.parse("2013-06-05"));
        holidaysMap.put(4009,LocalDate.parse("2013-06-06"));
        holidaysMap.put(4010,LocalDate.parse("2013-06-21"));
        holidaysMap.put(4011,LocalDate.parse("2013-11-01"));
        holidaysMap.put(4012,LocalDate.parse("2013-12-24"));
        holidaysMap.put(4013,LocalDate.parse("2013-12-25"));
        holidaysMap.put(4014,LocalDate.parse("2013-12-26"));
        holidaysMap.put(4015,LocalDate.parse("2013-12-31"));

        Map<String, String> configurationMap= new HashMap<>();
        configurationMap.put("freeYear","2013");
        configurationMap.put("country","Egypt");
        configurationMap.put("freeMonth","7");

        Weekends weekendsObj=new Weekends("Egypt","Friday,Saturday");

        this.freeVehicleAndDateService=new FreeVehicleAndDateServiceImpl(freeVehiclesMap,holidaysMap,configurationMap,weekendsObj);
    }

    @Test
    public void testIsTollFreeVehicle(){

        Vehicle motorBikeVehicle=new Motorbike();
        assertTrue(this.freeVehicleAndDateService.isTollFreeVehicle(motorBikeVehicle));

        Vehicle otherVehicle1=new OtherVehicle("Tractor");
        assertTrue(this.freeVehicleAndDateService.isTollFreeVehicle(otherVehicle1));

        Vehicle otherVehicle2=new OtherVehicle("Emergency");
        assertTrue(this.freeVehicleAndDateService.isTollFreeVehicle(otherVehicle2));

        Vehicle otherVehicle3=new OtherVehicle("Diplomat");
        assertTrue(this.freeVehicleAndDateService.isTollFreeVehicle(otherVehicle3));

        Vehicle otherVehicle4=new OtherVehicle("Foreign");
        assertTrue(this.freeVehicleAndDateService.isTollFreeVehicle(otherVehicle4));

        Vehicle otherVehicle5=new OtherVehicle("Military");
        assertTrue(this.freeVehicleAndDateService.isTollFreeVehicle(otherVehicle5));

        Vehicle carVehicle=new Car();
        assertFalse(this.freeVehicleAndDateService.isTollFreeVehicle(carVehicle));

    }

    @Test
    public void testIsTollFreeDate(){
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-01-01")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-03-28")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-05-01")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-06-21")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-11-01")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-12-25")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-12-31")));
        assertTrue(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-07-25")));

        assertFalse(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-08-21")));
        assertFalse(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-12-03")));
        assertFalse(this.freeVehicleAndDateService.isTollFreeDate(Date.valueOf("2013-11-17")));
    }
}
