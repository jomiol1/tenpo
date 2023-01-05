package com.tenpo.api.controller;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.api.dto.PercentageReqDTO;
import com.tenpo.api.service.AuditService;
import com.tenpo.api.service.PercentageService;


@WebMvcTest(value = CalculateController.class)
@AutoConfigureMockMvc
public class CalculateControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
    private PercentageService percentageService;
	
	@MockBean
	private AuditService auditService;
	
	PercentageReqDTO percentageReqDTO;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		percentageReqDTO = PercentageReqDTO.builder().number1(5.0).number2(5.0).build();
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void calculateTest() throws JsonProcessingException, Exception {
		
		when(percentageService.calculate(anyDouble(), anyDouble())).thenReturn(11.0);
		
		mvc.perform(post("/calculate").contentType(MediaType.APPLICATION_JSON).content(
				objectMapper.writeValueAsString(percentageReqDTO))).andExpect(status().isOk());
	}
	

}
