package com.tenpo.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "porcentaje")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PercentageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "porcentaje")
    private Double porcentaje;
    @Column(name = "fecha_porcentaje")
    private Date fechaPorcentaje;

}
