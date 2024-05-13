package com.proj.TollCalculator.services;

import com.proj.TollCalculator.models.app.Vehicle;
import com.proj.TollCalculator.models.request.RequestDate;

public interface TollCalculatorService {
    public int getTotalTollFee(Vehicle vehicleType, RequestDate requestDate);
}
