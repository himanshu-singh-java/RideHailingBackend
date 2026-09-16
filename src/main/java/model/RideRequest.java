package model;

public class RideRequest {

    private int riderId;
    private String vehicleType;
    private int pickupNode;
    private int dropNode;

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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
}
