package repository;

import model.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {

    @Query(value = "SELECT * FROM vehicles WHERE vehicle_type = :vType AND status = 'AVAILABLE' LIMIT 1", nativeQuery = true)
    Vehicles findAvailableVehicle(@Param("vType") String vehicleType);

}
