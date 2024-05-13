package com.proj.TollCalculator.models.JPA;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/***************************

 Holidays Entity for DB

 ***************************/

@Entity
@Table(name = "HOLIDAYS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Holidays {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "DAYMONTH")
    private String dayMonth;

}
