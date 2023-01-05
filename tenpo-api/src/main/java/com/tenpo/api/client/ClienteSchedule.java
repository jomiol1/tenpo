package com.tenpo.api.client;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.tenpo.api.dto.PercentageDTO;
import com.tenpo.api.entity.PercentageEntity;
import com.tenpo.api.handler.exception.GeneralException;
import com.tenpo.api.repository.PercentageRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class ClienteSchedule {
	
    @Autowired
    private PercentageRepository porcentajeRepository;
    
    @Autowired
    private RestTemplate restTemplate;
	
	@Scheduled(fixedDelay = 300000)
	public void scheduleFixedDelayTask() {
		try {
			PercentageDTO response= restTemplate.getForObject("http://localhost:8082/percentage", PercentageDTO.class);
			PercentageEntity percentageEntity = PercentageEntity.builder().porcentaje(response.getPercentage()).fechaPorcentaje(new Date()).build();
			porcentajeRepository.save(percentageEntity);
			log.info("Fecha {}, porcentaje {}",percentageEntity.getFechaPorcentaje(), percentageEntity.getPorcentaje().toString());
			
		} catch (Exception e) {
			throw new GeneralException(1, "El servicio externo no esta disponible");
		}
		

	}

}
