package com.tenpo.api.controller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tenpo.api.entity.PercentageEntity;
import com.tenpo.api.repository.PercentageRepository;
import com.tenpo.api.service.PercentageService;


@SpringBootTest
public class PercentageServiceTest {
	
	@MockBean
    private PercentageRepository porcentajeRepository;
	
	@Autowired
	private PercentageService percentageService;
	
	Optional<PercentageEntity> percentageOpt;
	PercentageEntity percentageEntity;
	
	@BeforeEach
	public void setUp() {
		percentageEntity = new PercentageEntity();
		percentageEntity.setPorcentaje(10.0);
		percentageOpt = Optional.of(percentageEntity);
	}
	
	@Test
	void calculateTest() {
		
		when(porcentajeRepository.findTopByOrderByIdDesc()).thenReturn(percentageOpt);
		
		assertEquals(11.0, percentageService.calculate(5.0, 5.0));
		
	}

}
