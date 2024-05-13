package com.proj.TollCalculator.models.JPA;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "WEEKENDS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Weekends {

    @Id
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "WEEKENDS")
    private String weekends;
}
