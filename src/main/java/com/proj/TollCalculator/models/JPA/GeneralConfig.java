package com.proj.TollCalculator.models.JPA;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENERAL_CONFIG")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeneralConfig {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;
}
