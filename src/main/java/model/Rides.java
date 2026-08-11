package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "rides")
public class Rides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private int rideId;

    @JoinColumn(name = "rider_id", nullable = false)
    @ManyToOne
    private Riders rider;

    @JoinColumn(name = "vehicle_id", nullable = false)
    @ManyToOne
    private Vehicles vehicle;

    @Column(name = "pickup_node", nullable = false)
    private int pickupNode;

    @Column(name = "drop_node", nullable = false)
    private int dropNode;

    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "fare")
    private Double fare;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;


    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public Rides(){

    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public Riders getRider() {
        return rider;
    }

    public void setRider(Riders rider) {
        this.rider = rider;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
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

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RideRepository{" +
                "rider=" + rider +
                ", vehicle=" + vehicle +
                '}';
    }
}
