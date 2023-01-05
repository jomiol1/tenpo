package com.tenpo.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "auditoria_requests")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uri")
    private String uri;
    @Column(name = "status")
    private Integer status;
    @Column(name = "response", columnDefinition = "TEXT")
    private String response;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

}
