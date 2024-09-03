package com.oneInfo.challenge.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneInfo.challenge.model.Path;
import com.oneInfo.challenge.service.PathService;

@SpringBootTest
@AutoConfigureMockMvc
public class PathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PathService pathService;
    
    @Autowired
    private StationController stationController;
    
    @Test
    public void addPathSuccess() throws Exception {
        Long pathId = 1L;
        Long destinationId = 2L;
        Path path = new Path();
        path.setSource_id(1L);
        path.setDestination_id(destinationId);
        path.setCost(10.0);
        
        mockMvc.perform(put("/stations/{id}", pathId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(path)))
                .andExpect(status().isOk());        
        
        mockMvc.perform(put("/stations/{id}", destinationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(path)))
                        .andExpect(status().isOk());

        mockMvc.perform(put("/paths/{id}", pathId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(path)))
                .andExpect(status().isOk());

        Path savedPath = pathService.findById(pathId).orElse(null);
        assertNotNull(savedPath);
        assertEquals(path.getSource_id(), savedPath.getSource_id());
    }
    
    @Test
    public void findShortestPathSuccess() throws Exception {
        Long sourceId = 1L;
        Long destinationId = 2L;
        
        mockMvc.perform(get("/paths/{source_id}/{destination_id}", sourceId, destinationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(10.0));
    }
}
