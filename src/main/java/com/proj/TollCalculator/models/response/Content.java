package com.proj.TollCalculator.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @JsonProperty("totalTollFee")
    private Integer totalTollFee;

}
