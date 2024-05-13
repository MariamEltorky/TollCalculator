package com.proj.TollCalculator.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDate {

    @JsonProperty("date")
    @NotNull
    private Date date;

    @JsonProperty("times")
    @NotNull
    private List<Time> times;

}
