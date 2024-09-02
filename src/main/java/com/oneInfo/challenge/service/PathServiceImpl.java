package com.oneInfo.challenge.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oneInfo.challenge.model.Path;
import com.oneInfo.challenge.model.ShortestPathResult;
import com.oneInfo.challenge.model.Station;
import com.oneInfo.challenge.repository.PathRepository;
import com.oneInfo.challenge.repository.StationRepository;

@Service
public class PathServiceImpl implements PathService{
	
	@Autowired
	StationRepository stationRepository;
	
	@Autowired
	PathRepository pathRepository;
		
	public ResponseEntity<String> addPath(Path path) {
		try {
			if(path.getSource_id() > path.getDestination_id()) {
				//Ordeno los ids de las estaciones para que siempre quede el menor como Source_id
				Long aux = path.getSource_id();
				path.setSource_id(path.getDestination_id());
				path.setDestination_id(aux);
			} else if(path.getPath_id() == path.getDestination_id()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("El id del camino de incio y fin no pueden ser el mismo ");
			}
			
			
			if(!this.haveStation(path.getSource_id(),path.getDestination_id())) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("El id del inicio o fin no contiene ninguna estación asignada");
			}
			
			if(!pathRepository.findById(path.getPath_id()).isEmpty()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("El id del inicio o fin no contiene ninguna estación asignada");
				
			}
			
			pathRepository.save(path);
			
			return ResponseEntity.status(HttpStatus.OK).body("Camino Agregado exitosamente");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocurrio un error + " + e);
		}
	
	
		
	}
	
	private Boolean haveStation(Long idStationA,Long idStationB){
		Optional<Station> stationSourceId = stationRepository.findById(idStationA);
		Optional<Station> stationDestinationId = stationRepository.findById(idStationB);
		if(stationSourceId.isEmpty() || stationDestinationId.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
	

	/*public PathShort findShortestPath(Long start, Long end) {
		return null;
	}*/
	
	public ShortestPathResult findShortestPath(Long sourceId, Long destinationId) {
        Map<Long, Double> minDistances = new HashMap<>();
        Map<Long, Long> previousNodes = new HashMap<>();
        // Cola de prioridad para elegir el nodo con menor costo
        PriorityQueue<PathNode> priorityQueue = new PriorityQueue<>(Comparator.comparing(PathNode::getCost));

        // Inicializar todos los costos como infinitos excepto el nodo de origen
        for (Path path : pathRepository.getAllPaths()) {
            minDistances.put(path.getSource_id(), Double.MAX_VALUE);
            minDistances.put(path.getDestination_id(), Double.MAX_VALUE);
        }
        minDistances.put(sourceId, 0.0);

        // Agregar nodo de origen a la cola
        priorityQueue.add(new PathNode(sourceId, 0.0));

        // Algoritmo de Dijkstra
        while (!priorityQueue.isEmpty()) {
            PathNode currentNode = priorityQueue.poll();
            Long currentNodeId = currentNode.getNodeId();

            // Si llegamos al nodo destino, no necesitamos continuar
            if (currentNodeId.equals(destinationId)) {
                break;
            }

            List<Path> adjacentPaths = pathRepository.findPathsBySource(currentNodeId);

            for (Path path : adjacentPaths) {
                Long adjacentNodeId = path.getDestination_id();
                Double newCost = minDistances.get(currentNodeId) + path.getCost();

                if (newCost < minDistances.get(adjacentNodeId)) {
                    minDistances.put(adjacentNodeId, newCost);
                    previousNodes.put(adjacentNodeId, currentNodeId);
                    priorityQueue.add(new PathNode(adjacentNodeId, newCost));
                }
            }
        }

        // Reconstruir el camino desde destinationId a sourceId
        List<Long> shortestPath = new LinkedList<>();
        Long currentNodeId = destinationId;
        while (currentNodeId != null) {
            shortestPath.add(0, currentNodeId);
            currentNodeId = previousNodes.get(currentNodeId);
        }

        // Si el primer nodo en el camino no es el nodo de origen, no hay camino
        if (shortestPath.isEmpty() || !shortestPath.get(0).equals(sourceId)) {
            return new ShortestPathResult(Collections.emptyList(), 0D);
        }

        return new ShortestPathResult(shortestPath, minDistances.get(destinationId));
    }

    // Clase auxiliar para almacenar nodo y su costo acumulado
    private static class PathNode {
        private final Long nodeId;
        private final Double cost;

        public PathNode(Long nodeId, Double cost) {
            this.nodeId = nodeId;
            this.cost = cost;
        }

        public Long getNodeId() {
            return nodeId;
        }

        public Double getCost() {
            return cost;
        }
    }


}
