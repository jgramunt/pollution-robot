package com.seat.pollutionrobot.gps.impl;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.gps.GPS;
import com.seat.pollutionrobot.gps.impl.FakeGPSUtils.FakeGPSCalculator;

import java.util.List;

public class FakeGPS implements GPS {

    double speed;
    long ongoingTime;
    List<LatLng> pointList;
    FakeGPSCalculator calculator = new FakeGPSCalculator();

    double actualDistance;

    public FakeGPS(String polyline, long ongoingTime, double speed) {
        this.ongoingTime = ongoingTime;
        this.speed = speed;
        calculateWalkedDistance();
        getPointListFromPolyline(polyline);
    }

    @Override
    public LatLng getLocation() {
        return calculator.calculateActualPositionOfPolylineFromDistance(pointList, actualDistance);
    }

    public double getPolylineDistance() {
        double totalMeters = 0;
        LatLng pointOrigin = pointList.get(0);
        LatLng pointDestination;
        for (LatLng point : pointList) {
            if (point == pointList.get(0)) {
                continue;
            }
            pointDestination = point;
            totalMeters += calculator.calculateDistanceBetweenTwoPoints(pointOrigin, pointDestination);
            pointOrigin = point;
        }
        return totalMeters;
    }

    private void calculateWalkedDistance() {
        actualDistance = (ongoingTime / 1000) * speed;
    }

    private void getPointListFromPolyline(String polyline) {
        EncodedPolyline encodedPolyline = new EncodedPolyline(polyline);
        pointList = encodedPolyline.decodePath();
    }


}
