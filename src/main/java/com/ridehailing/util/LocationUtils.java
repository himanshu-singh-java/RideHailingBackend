package com.ridehailing.util;

public class LocationUtils {
    private static final int EARTH_RADIUS_KM = 6371;

    public static Double calculateDistance(Double startLat, Double startLng, Double endLat, Double endLng){

        Double latDistance = Math.toRadians(endLat - startLat);
        Double lngDistance = Math.toRadians(endLng - startLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(endLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
