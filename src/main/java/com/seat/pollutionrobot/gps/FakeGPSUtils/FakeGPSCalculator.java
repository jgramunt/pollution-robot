package com.seat.pollutionrobot.gps.FakeGPSUtils;

import com.google.maps.model.LatLng;

import java.util.List;

public class FakeGPSCalculator {

    public LatLng calculateActualPositionOfPolylineFromDistance(List<LatLng> pointList, double walkedDistance) {
        double addedPointDistance = 0;
        LatLng pointOrigin = pointList.get(0);
        LatLng pointDestination;
        for (LatLng point : pointList) {
            if (point == pointList.get(0)) {
                continue;
            }
            if (addedPointDistance == walkedDistance) {
                return pointOrigin;
            }
            pointDestination = point;
            double nextPointDistance = addedPointDistance + calculateDistanceBetweenTwoPoints(pointOrigin, pointDestination);
            if (nextPointDistance > walkedDistance) {
                return calculateNewLatLngBetweenTwoPoints(pointOrigin, pointDestination, walkedDistance - addedPointDistance);
            }
            addedPointDistance = nextPointDistance;
            pointOrigin = point;
        }
        return null;
    }

    public double calculateDistanceBetweenTwoPoints(LatLng pointA, LatLng pointB) {
        double lat1 = pointA.lat;
        double lng1 = pointA.lng;
        double lat2 = pointB.lat;
        double lng2 = pointB.lng;

        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (earthRadius * c);

        return dist;
    }

    public LatLng calculateNewLatLngBetweenTwoPoints(LatLng latLngOrigin, LatLng latLngDestination, double extraMeters) {
        double distanceBetweenPoints = calculateDistanceBetweenTwoPoints(latLngOrigin, latLngDestination);
        LatLng newLatLng = new LatLng();
        double extraMetersDividedByDistance = extraMeters / distanceBetweenPoints;
        newLatLng.lat = latLngOrigin.lat + (latLngDestination.lat - latLngOrigin.lat) * extraMetersDividedByDistance;
        newLatLng.lng = latLngOrigin.lng + (latLngDestination.lng - latLngOrigin.lng) * extraMetersDividedByDistance;

        return newLatLng;
    }

}
