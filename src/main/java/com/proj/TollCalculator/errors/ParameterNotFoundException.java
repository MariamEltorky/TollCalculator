package com.proj.TollCalculator.errors;

/*****************************************

 If any parameter of request is Not Found

 *****************************************/

public class ParameterNotFoundException extends RuntimeException {

    public ParameterNotFoundException(String message) {
        super(message);
    }
}
