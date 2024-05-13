package com.proj.TollCalculator.models.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*******************

 Other Vehicle Class

 *******************/

@Setter
@Getter
@AllArgsConstructor
public class OtherVehicle implements Vehicle {
    private final String type;
}
