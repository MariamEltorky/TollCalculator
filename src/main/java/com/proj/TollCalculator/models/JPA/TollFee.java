package com.proj.TollCalculator.models.JPA;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

/***************************

 TollFee Entity for DB

 ***************************/

@Entity
@Table(name = "TOLL_FEE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TollFee {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "STARTTIME")
    private Time startTime;

    @Column(name = "ENDTIME")
    private Time endTime;

    @Column(name = "FEE")
    private int fee;

}
