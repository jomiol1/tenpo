package com.tenpo.api.controller;

import com.tenpo.api.dto.IncrementoDTO;
import com.tenpo.api.dto.PercentageReqDTO;
import com.tenpo.api.service.PercentageService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import java.time.Duration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
 	 private Bucket bucket;
	
    public CalculateController() {
  
    	
        Bandwidth limit = Bandwidth.classic(50, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }


    @Autowired
    PercentageService percentageService;
    

    @PostMapping("")
    public ResponseEntity<?> calculate(@RequestBody @Valid PercentageReqDTO request) {

        IncrementoDTO incremento = IncrementoDTO.builder()
                .incremento(percentageService.calculate(request.getNumber1(), request.getNumber2()))
                .build();

        return new ResponseEntity<>(incremento, HttpStatus.OK);
    }


}
