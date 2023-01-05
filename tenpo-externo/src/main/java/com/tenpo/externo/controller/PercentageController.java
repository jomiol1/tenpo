package com.tenpo.externo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpo.externo.dto.PercentageDTO;

import java.util.Random;

@RestController
public class PercentageController {

    @GetMapping("percentage")
    public ResponseEntity<?> getPercentage() {
        Random random = new Random();
        PercentageDTO porcentajeDTO = PercentageDTO.builder()
                .percentage(random.nextDouble(100.0 - 1.0 + 1) + 1.0)
                .build();
        return new ResponseEntity<>(porcentajeDTO, HttpStatus.OK);
    }
}
