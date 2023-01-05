package com.tenpo.externo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebMvcTest(value = PercentageController.class)
@AutoConfigureMockMvc
public class PercentageControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	public void setUp() {
		
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	void obtenerPorcentaje() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/percentage").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

}
