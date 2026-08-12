package routing;

import java.util.*;

public class CityMap {
    private Map<Integer, List<Edge>> graph = new HashMap<>();

    public void addRoad(int source, int destination, Double distance){
        graph.putIfAbsent(source, new ArrayList<>());
        graph.putIfAbsent(destination, new ArrayList<>());

        graph.get(source).add(new Edge(destination, distance));
        graph.get(destination).add(new Edge(source, distance));
    }

    public Double getShortestDistance(int source, int destination){
        if(!graph.containsKey(source) || !graph.containsKey(destination)){
            System.out.println("Error: Invalid pickup or drop location!");
            return -1.0;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getDistance));

        Map<Integer, Double> minDistanceMap = new HashMap<>();

        for(Integer node : graph.keySet()){
            minDistanceMap.put(node, Double.MAX_VALUE);
        }

        pq.add(new Edge(source, 0.0));
        minDistanceMap.put(source, 0.0);

        while (!pq.isEmpty()){
            Edge currentEdge = pq.poll();
            int currentNode = currentEdge.getDestinationNode();
            Double currentDist = currentEdge.getDistance();

            if(currentNode == destination){
                return currentDist;
            }

            if(currentDist > minDistanceMap.get(currentNode)){
                continue;
            }

            for(Edge neighbour : graph.get(currentNode)){
                double newDist = currentDist + neighbour.getDistance();

                if(newDist < minDistanceMap.get(neighbour.getDestinationNode())){
                    minDistanceMap.put(neighbour.getDestinationNode(), newDist);
                    pq.add(new Edge(neighbour.getDestinationNode(), newDist));
                }
            }
        }

        System.out.println("No path found between " + source + " and " + destination);
        return  -1.0;
    }
}
