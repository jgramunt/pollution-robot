package com.seat.pollutionrobot.gps;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.gps.fake_gps_utils.FakeGPSCalculator;

import java.util.List;

public class FakeGPS {

    FakeGPSCalculator calculator = new FakeGPSCalculator();

    private String polyline;
    private double speed;

    private List<LatLng> polylinePointList;
    private LatLng actualPosition;
    private double polylineLength;
    private double currentDistance;


    private long ongoingTime;

    public FakeGPS(String polyline, long ongoingTime, double speed) {
        this.polyline = polyline;
        this.ongoingTime = ongoingTime;
        this.speed = speed;
        startGPS();
    }

    public double getPolylineLength() {
        return polylineLength;
    }

    public LatLng getActualPosition() {
        return actualPosition;
    }

    private void startGPS() {
        setPointListFromPolyline();
        setPolylineDistance();
        calculateCurrentDistance();
        refreshLocation();
    }

    private void setPointListFromPolyline() {
        EncodedPolyline encodedPolyline = new EncodedPolyline(polyline);
        polylinePointList = encodedPolyline.decodePath();
    }

    private void setPolylineDistance() {
        double totalMeters = 0;
        LatLng pointOrigin = polylinePointList.get(0);
        LatLng pointDestination;
        for (LatLng point : polylinePointList) {
            if (point == polylinePointList.get(0)) {
                continue;
            }
            pointDestination = point;
            totalMeters += calculator.calculateDistanceBetweenTwoPoints(pointOrigin, pointDestination);
            pointOrigin = point;
        }
        polylineLength = totalMeters;
    }

    private void calculateCurrentDistance() {
        currentDistance = (ongoingTime / 1000) * speed;
    }

    private void refreshLocation() {
        actualPosition = calculator.calculateActualPositionOfPolylineFromDistance(polylinePointList, currentDistance);
    }

}
