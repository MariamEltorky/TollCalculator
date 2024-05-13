package com.proj.TollCalculator.config;

import com.proj.TollCalculator.models.JPA.*;
import com.proj.TollCalculator.repositories.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/******************************************************

 Configurations to load data from DB and create beans

 ******************************************************/
@Configuration
public class AppConfig {

    static Logger logger = LogManager.getLogger(AppConfig.class);
    @Autowired
    GeneralConfigRepo generalConfigRepo;

    @Autowired
    VehiclesRepo vehiclesRepo;

    @Autowired
    HolidaysRepo holidaysRepo;

    @Autowired
    TollFeeRepo tollFeeRepo;

    @Autowired
    WeekendsRepo weekendsRepo;

    public static Map<String, String> configurationMap = new HashMap<>();
    public static Map<Integer, String> freeVehiclesMap = new HashMap<>();
    public static Map<Integer, LocalDate> holidaysMap = new HashMap<>();
    public static Map<Integer, TollFee> tollFeeMap = new HashMap<>();
    public static Weekends weekendsObj=new Weekends();

    @Bean
    public Map<String,String> configureMap(){
        logger.info("AppConfig - Start executing configureMapBean");
        List<GeneralConfig> configuration=generalConfigRepo.findAll();
        configuration.stream().forEach(config->{
            configurationMap.put(config.getKey(),config.getValue());
        });
        logger.info("Loaded Configuration - {}",configurationMap.toString());

        logger.info("AppConfig - End executing configureMapBean");
        return configurationMap;
    }

    @Bean
    public Weekends weekends(){
        logger.info("AppConfig - Start executing weekendsBean");
        List<Weekends> weekendsList=weekendsRepo.findAll();
        GeneralConfig generalConfig=generalConfigRepo.findByKey("country");
        String country = generalConfig.getValue();
        weekendsList.stream().forEach(weekend->{
            if (weekend.getCountry().equalsIgnoreCase(country)) {
                weekendsObj = weekend;
            }
        });
        logger.info("Loaded Weekends - {}",weekendsObj.toString());

        logger.info("AppConfig - End executing weekendsBean");
        return weekendsObj;
    }

    //FreeVehicles Data Bean
    @Bean
    public Map<Integer, String> freeVehicles() {
        logger.info("AppConfig - Start executing freeVehiclesBean");
        List<Vehicles> freeVehiclesList = vehiclesRepo.findAll();
        freeVehiclesList.forEach(vehicle -> {
            if (vehicle.isFree()) {
                freeVehiclesMap.put(vehicle.getId(), vehicle.getVehicleType());
            }
        });
        logger.info("Loaded FreeVehicles - {}", freeVehiclesMap.toString());

        logger.info("AppConfig - End executing freeVehiclesBean");
        return freeVehiclesMap;
    }

    //Holidays Data Bean
    @Bean
    public Map<Integer, LocalDate> Holidays() {
        logger.info("AppConfig - Start executing HolidaysBean");
        List<Holidays> holidaysList = holidaysRepo.findAll();
        GeneralConfig generalConfig=generalConfigRepo.findByKey("freeYear");
        String year = generalConfig.getValue();

        holidaysList.forEach(holiday -> {
            LocalDate localDate = LocalDate.parse(year + "-" + holiday.getDayMonth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            holidaysMap.put(holiday.getId(),localDate);
        });
        logger.info("Loaded Holidays - {}", holidaysMap.toString());

        logger.info("AppConfig - End executing HolidaysBean");
        return holidaysMap;
    }

    //TollFee Data Bean
    @Bean
    public Map<Integer, TollFee> TollFee() {
        logger.info("AppConfig - Start executing TollFeeBean");
        List<TollFee> tollFeesList = tollFeeRepo.findAll();
        tollFeesList.forEach(tollFee -> {
            tollFeeMap.put(tollFee.getId(), tollFee);
        });
        logger.info("Loaded TollFees - {}", tollFeeMap.toString());

        logger.info("AppConfig - End executing TollFeeBean");
        return tollFeeMap;
    }


}
