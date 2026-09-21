package repository;

import model.RideStatus;
import model.Rides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RideRepository extends JpaRepository<Rides, Integer> {

    List<Rides> findByRider_RiderIDOrderByRideIdDesc(int riderId);

    boolean existsByRider_RiderIDAndRideStatus(int riderID, RideStatus rideStatus);
}