package com.proj.TollCalculator.services;

import com.proj.TollCalculator.models.app.Vehicle;

import java.sql.Date;

public interface FreeVehicleAndDateService {
    public boolean isTollFreeVehicle(Vehicle vehicleType);

    public boolean isTollFreeDate(Date date);

}
