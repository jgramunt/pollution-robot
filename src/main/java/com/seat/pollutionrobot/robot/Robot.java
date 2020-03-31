package com.seat.pollutionrobot.robot;

import com.seat.pollutionrobot.fake_gps.FakeGPS;

import java.util.Date;

public class Robot {

    private DistanceCalculator distanceCalculator = new DistanceCalculator();

    private boolean isRobotOn;

    private String polyline;
    private String actualPosition;
    private double polylineLength;
    private double currentDistance;

    private long timeSendInterval = 900000; //15 minutes in milliseconds
    private double distanceRegistrationInterval = 100; //in meters
    private double speed = 2;

    private long startTimestamp;
    private long currentTimestamp;
    private long ongoingTime;

    private double distanceOnLastRegister;
    private long timeOnLastRegister;

    public void start() {
        startTimestamp = new Date().getTime();
        polylineLength = distanceCalculator.getPolylineDistance();
        isRobotOn = true;
        robotIsOn();
    }

    public void stop() {
        isRobotOn = false;
    }

    public double readActualPosition() {
        FakeGPS fakeGPS = new FakeGPS(polyline);
        return Double.parseDouble(null);
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public double getCurrentDistance() {
        currentDistance = distanceCalculator.getActualDistance(speed, ongoingTime);
        return currentDistance;
    }

    public void setSpeed(double speed) {
        if (speed < 1 || speed > 3) {
            //TODO throw Exception so User can see a message
        } else {
            this.speed = speed;
        }
    }



    private void robotIsOn() {
        while (isRobotOn) {
            updateTime();
            updateActualDistance();

            stopIfPolylineHasEnded();
            makeRegistrationIfNeeded();
            sendRegistersMeanIfNeeded();
        }
    }

    private void stopIfPolylineHasEnded() {
        if (currentDistance >= polylineLength) {
            stop();
        }
    }


    private void updateTime() {
        currentTimestamp = new Date().getTime();
        ongoingTime = currentTimestamp - startTimestamp;
    }

    private void updateActualDistance() {
        currentDistance = speed * ongoingTime;
    }

    private void makeRegistrationIfNeeded() {
        double distanceSinceLastRegister = currentDistance - distanceOnLastRegister;
        if (distanceSinceLastRegister >= distanceRegistrationInterval) {
            doPollutionRegistration();
            distanceOnLastRegister = currentDistance;
        }
    }


    private void sendRegistersMeanIfNeeded() {
        long timeSinceLastSend = currentTimestamp - timeOnLastRegister;
        if (timeSinceLastSend >= timeSendInterval) {
            doSendPollutionInformation();
            timeOnLastRegister = currentTimestamp;
        }
    }



    private void doPollutionRegistration() {
        //TODO pollution registration
    }
    private void doSendPollutionInformation() {
        //TODO send pollution information
    }

}
