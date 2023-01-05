package com.tenpo.api.service;

import com.tenpo.api.entity.PercentageEntity;
import com.tenpo.api.handler.exception.GeneralException;
import com.tenpo.api.repository.PercentageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PercentageService {

    
    @Autowired
    private PercentageRepository porcentajeRepository;

    public Double calculate(Double value1, Double value2) {
    	
    	PercentageEntity percengajeEntity = porcentajeRepository.findTopByOrderByIdDesc().orElseThrow(()->
    		new GeneralException(1, "No es posible calcular el porcentaje")
    	);
    	
    	Double increment = 1.0 + (percengajeEntity.getPorcentaje() / 100);

        return (value1 + value2) * increment;
    }

}
