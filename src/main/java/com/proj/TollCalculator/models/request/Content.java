package com.proj.TollCalculator.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @JsonProperty("vehicleType")
    @NotNull
    @Valid
    private String vehicleType;

    @JsonProperty("dates")
    @Valid
    private RequestDate dates;
}
