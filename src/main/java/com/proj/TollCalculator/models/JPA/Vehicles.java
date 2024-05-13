package com.proj.TollCalculator.models.JPA;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***************************

 FreeVehicles Entity for DB

 ***************************/

@Entity
@Table(name = "VEHICLES")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "VEHICLETYPE")
    private String vehicleType;

    @Column(name = "ISFREE")
    private boolean isFree;
}
