package model;

public class RideResponse {

    private int rideId;

    private int pickupNode;

    private int dropNode;

    private double fare;

    private String status;

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getPickupNode() {
        return pickupNode;
    }

    public void setPickupNode(int pickupNode) {
        this.pickupNode = pickupNode;
    }

    public int getDropNode() {
        return dropNode;
    }

    public void setDropNode(int dropNode) {
        this.dropNode = dropNode;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
