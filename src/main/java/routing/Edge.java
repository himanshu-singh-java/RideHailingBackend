package routing;

public class Edge {
    private int destinationNode;
    private Double distance;

    public Edge(int destinationNode, Double distance){
        this.destinationNode = destinationNode;
        this.distance = distance;
    }

    public int getDestinationNode() {
        return destinationNode;
    }

    public Double getDistance() {
        return distance;
    }
}
