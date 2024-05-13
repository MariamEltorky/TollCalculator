package com.proj.TollCalculator.models.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*******************

 Motorbike Vehicle Class

 *******************/

@Setter
@Getter
@AllArgsConstructor
public class Motorbike implements Vehicle {
    @Override
    public String getType() {
        return "Motorbike";
    }
}

