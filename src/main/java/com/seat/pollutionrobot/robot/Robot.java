package com.seat.pollutionrobot.robot;

import com.seat.pollutionrobot.fake_gps.FakeGPS;

import java.util.Date;

public class Robot {

    private DistanceCalculator distanceCalculator = new DistanceCalculator();

    private boolean isRobotOn;

    private String polyline;
    private String actualPosition;
    private double polylineDistance;

    private double speed = 2;
    private double actualDistance;

    private long startTimestamp;
    private long currentTimestamp;
    private double ongoingTime;

    public void start() {
        startTimestamp = new Date().getTime();
        isRobotOn = true;
        robotIsOn();
    }

    public void stop() {
        isRobotOn = false;
    }

    public String report() {
        return null;
    }

    public double readActualPosition() {
        FakeGPS fakeGPS = new FakeGPS(polyline);
        fakeGPS.getActualPosition();
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public double getActualDistance() {
        actualDistance = distanceCalculator.getActualDistance(speed, ongoingTime);
        return actualDistance;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }



    private void robotIsOn() {
        while (isRobotOn) {
            currentTimestamp = new Date().getTime();

        }
    }

    private long getOngoingTime() {
        ongoingTime = currentTimestamp - startTimestamp;
        return getOngoingTime();
    }

}
