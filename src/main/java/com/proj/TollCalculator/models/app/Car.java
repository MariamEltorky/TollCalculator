package com.proj.TollCalculator.models.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*******************

 Car Vehicle Class

 *******************/

@Setter
@Getter
@AllArgsConstructor
public class Car implements Vehicle {
    @Override
    public String getType() {
        return "Car";
    }
}
