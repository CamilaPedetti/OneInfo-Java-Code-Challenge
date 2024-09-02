package com.oneInfo.challenge.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oneInfo.challenge.model.Path;
import com.oneInfo.challenge.model.ShortestPathResult;
import com.oneInfo.challenge.service.PathServiceImpl;

public class PathServiceTest {

    @Mock
    private PathRepository pathRepository;

    @InjectMocks
    private PathServiceImpl pathService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testFindShortestPathSuccess() {
        Long sourceId = 1L;
        Long destinationId = 3L;

        Path path1 = new Path(1L, sourceId, 2L, 10.0);
        Path path2 = new Path(2L, 2L, destinationId, 5.0);
        Path path3 = new Path(3L, sourceId, destinationId, 20.0);

        when(pathRepository.getAllPaths()).thenReturn(Arrays.asList(path1, path2, path3));
        when(pathRepository.findPathsBySource(sourceId)).thenReturn(Arrays.asList(path1, path3));
        when(pathRepository.findPathsBySource(2L)).thenReturn(Arrays.asList(path2));
        when(pathRepository.findPathsBySource(destinationId)).thenReturn(Arrays.asList());


        ShortestPathResult result = pathService.findShortestPath(sourceId, destinationId);


        List<Long> expectedPath = Arrays.asList(sourceId, 2L, destinationId);
        assertEquals(expectedPath, result.getPath());
        assertEquals(15D, result.getTotalCost(), 0.0001);
    }
    
    @Test
    public void testFindShortestPathNoPathExists() {
        Long sourceId = 1L;
        Long destinationId = 3L;

        Path path1 = new Path(1L, sourceId, 2L, 10.0);
        Path path2 = new Path(2L, 2L, 4L, 5.0);

        when(pathRepository.getAllPaths()).thenReturn(Arrays.asList(path1, path2));
        when(pathRepository.findPathsBySource(sourceId)).thenReturn(Arrays.asList(path1));
        when(pathRepository.findPathsBySource(2L)).thenReturn(Arrays.asList(path2));
        when(pathRepository.findPathsBySource(destinationId)).thenReturn(Arrays.asList());

        ShortestPathResult result = pathService.findShortestPath(sourceId, destinationId);

        assertEquals(Arrays.asList(), result.getPath());
        assertEquals(0D, result.getTotalCost(), 0.0001);
    }
}
