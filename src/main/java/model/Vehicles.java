package model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;


    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "current_node")
    private int currentNode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VehicleStatus vehicleStatus;

    @Column(name = "rating", precision = 3, scale = 2)
    private double rating;

    Vehicles(){

    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(int currentNode) {
        this.currentNode = currentNode;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public abstract double calculateFare(double distanceKm);

    @Override
    public String toString() {
        return "Vehicles{" +
                "vehicleId=" + vehicleId +
                ", driverName='" + driverName + '\'' +
                ", currentNode=" + currentNode +
                ", vehicleStatus=" + vehicleStatus +
                ", rating=" + rating +
                '}';
    }
}
