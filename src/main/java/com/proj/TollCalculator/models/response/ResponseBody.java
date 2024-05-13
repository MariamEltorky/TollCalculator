package com.proj.TollCalculator.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/***************************

 ResponseBody For Example:
 {
     "content": {
        "totalTollFee": 44
     }
 }

 ***************************/

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody {

    @JsonProperty("content")
    public Content content;
}
