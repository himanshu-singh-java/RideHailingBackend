package model;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicles{

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_category")
    private String carCategory;

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @Column(name = "is_ac_available")
    private boolean isAcAvailable;

    @Column(name = "has_luggage_carrier")
    private boolean hasLuggageCarrier;

    @Column(name = "has_wifi_or_screen")
    private boolean hasWifiOrScreen;

    public Car(){

    }

    @Override
    public double calculateFare(double distanceKm){
        double baseFare = 50.0;
        double perKmRate = 12.0;

        if(this.carCategory != null && this.carCategory.equalsIgnoreCase("SUV")){
            perKmRate = 18.0;
            baseFare = 100.0;
        }
        return baseFare + (distanceKm * perKmRate);
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public boolean isAcAvailable() {
        return isAcAvailable;
    }

    public void setAcAvailable(boolean acAvailable) {
        isAcAvailable = acAvailable;
    }

    public boolean isHasLuggageCarrier() {
        return hasLuggageCarrier;
    }

    public void setHasLuggageCarrier(boolean hasLuggageCarrier) {
        this.hasLuggageCarrier = hasLuggageCarrier;
    }

    public boolean getHasWifiOrScreen() {
        return hasWifiOrScreen;
    }

    public void setHasWifiOrScreen(boolean hasWifiOrScreen) {
        this.hasWifiOrScreen = hasWifiOrScreen;
    }
}
