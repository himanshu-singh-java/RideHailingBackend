package com.ridehailing.model;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VehicleStatus vehicleStatus;

    @Column(name = "rating")
    private double rating;

    @Column(name = "current_latitude")
    private Double currentLat;

    @Column(name = "current_longitude")
    private Double currentLng;

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

    public Double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(Double currentLat) {
        this.currentLat = currentLat;
    }

    public Double getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(Double currentLng) {
        this.currentLng = currentLng;
    }

    public abstract double calculateFare(double distanceKm);

    @Override
    public String toString() {
        return "Vehicles{" +
                "vehicleId=" + vehicleId +
                ", driverName='" + driverName + '\'' +
                ", vehicleStatus=" + vehicleStatus +
                ", rating=" + rating +
                ", currentLat=" + currentLat +
                ", currentLng=" + currentLng +
                '}';
    }
}
