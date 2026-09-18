package mapper;

import model.RideRequest;
import model.RideResponse;
import model.Rides;

public class RideMapper {

    public static Rides mapToEntity(RideRequest request){

        Rides rides = new Rides();

        rides.setPickupNode(request.getPickupNode());
        rides.setDropNode(request.getDropNode());

        return rides;
    }

    public static RideResponse mapToResponse(Rides rides){
        RideResponse response = new RideResponse();

        response.setRideId(rides.getRideId());
        response.setPickupNode(rides.getPickupNode());
        response.setDropNode(rides.getDropNode());
        response.setFare(rides.getFare());
        response.setStatus(rides.getRideStatus().toString());

        return response;
    }
}
