package com.oneInfo.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.oneInfo.challenge.service.StationService;

@WebMvcTest(StationController.class)
public class StationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StationService stationService;
	
	@Test
	void contextLoads() {
	}
	
	public void addStationSuccess() throws Exception {
		mockMvc.perform(put("/stations/1").contentType(MediaType.APPLICATION_JSON)
		        .content("Buenos Aires")).andExpect(status().isOk());
	}
}
