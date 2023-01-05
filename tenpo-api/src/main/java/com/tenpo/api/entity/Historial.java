package com.tenpo.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Historial {
    private Integer pages;
    private Long total;
    private Integer totalResult;
    private List<?> data;
}
