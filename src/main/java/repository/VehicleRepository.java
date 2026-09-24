package repository;

import model.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {

    @Query(
            value = "SELECT * FROM vehicles v " +
                    "WHERE v.status = 'AVAILABLE' " +
                    "AND v.vehicle_type = :vehicleType " +
                    "AND (6371 * acos(cos(radians(:pickupLat)) * cos(radians(v.current_latitude)) " +
                    "* cos(radians(v.current_longitude) - radians(:pickupLng)) " +
                    "+ sin(radians(:pickupLat)) * sin(radians(v.current_latitude)))) <= :radius " +
                    "ORDER BY (6371 * acos(cos(radians(:pickupLat)) * cos(radians(v.current_latitude)) " +
                    "* cos(radians(v.current_longitude) - radians(:pickupLng)) " +
                    "+ sin(radians(:pickupLat)) * sin(radians(v.current_latitude)))) ASC " +
                    "LIMIT 1", nativeQuery = true
    )
    Vehicles findNearestAvailableVehicle(@Param("vehicleType") String vehicleType,
                                         @Param("pickupLat") Double pickupLat,
                                         @Param("pickupLng") Double pickupLng,
                                         @Param("radius") int radius);

}
