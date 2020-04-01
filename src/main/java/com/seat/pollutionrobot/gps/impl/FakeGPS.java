package com.seat.pollutionrobot.gps.impl;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.gps.GPS;
import com.seat.pollutionrobot.gps.impl.FakeGPSUtils.FakeGPSCalculator;
import com.seat.pollutionrobot.robot.RobotMemory;

public class FakeGPS implements GPS {

    RobotMemory m;
    FakeGPSCalculator calculator = new FakeGPSCalculator();

    public FakeGPS(RobotMemory robotMemory) {
        this.m = robotMemory;
        calculateCurrentDistance();
        getPointListFromPolyline(m.polyline);
    }

    @Override
    public LatLng getLocation() {
        return calculator.calculateActualPositionOfPolylineFromDistance(m.polylinePointList, m.currentDistance);
    }

    public double getPolylineDistance() {
        double totalMeters = 0;
        LatLng pointOrigin = m.polylinePointList.get(0);
        LatLng pointDestination;
        for (LatLng point : m.polylinePointList) {
            if (point == m.polylinePointList.get(0)) {
                continue;
            }
            pointDestination = point;
            totalMeters += calculator.calculateDistanceBetweenTwoPoints(pointOrigin, pointDestination);
            pointOrigin = point;
        }
        return totalMeters;
    }

    private void calculateCurrentDistance() {
        m.currentDistance = (m.ongoingTime / 1000) * m.speed;
    }

    private void getPointListFromPolyline(String polyline) {
        EncodedPolyline encodedPolyline = new EncodedPolyline(polyline);
        m.polylinePointList = encodedPolyline.decodePath();
    }


}
