package com.proj.TollCalculator.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/*****************************************

 TollCalculatorRequestBody For Example:
 {
     "content": {
         "vehicleType": "Car",
         "dates": {
             "date": "2013-08-28",
             "times": [
                 "08:15:49",
                 "08:31:49",
                 "16:00:49",
                 "07:29:49"
             ]
         }
     }
 }

 *****************************************/

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TollCalculatorRequestBody {
    @JsonProperty("content")
    @Valid
    private Content content;
}
