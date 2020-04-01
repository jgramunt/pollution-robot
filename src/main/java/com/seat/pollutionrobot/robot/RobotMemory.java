package com.seat.pollutionrobot.robot;

import com.google.maps.model.LatLng;

import java.util.List;

public class RobotMemory {

    public boolean isRobotOn;

    public String polyline = "";
    public List<LatLng> polylinePointList;
    public LatLng actualPosition;
    public double polylineLength;
    public double currentDistance;

    public long timeSendInterval = 900000; //15 minutes in milliseconds
    public double distanceRegistrationInterval = 100; //in meters
    public double speed = 2;

    public long startTimestamp;
    public long currentTimestamp;
    public long ongoingTime;

    public double distanceOnLastRegister;
    public long timeOnLastRegister;

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public LatLng getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(LatLng actualPosition) {
        this.actualPosition = actualPosition;
    }

    public double getPolylineLength() {
        return polylineLength;
    }

    public void setPolylineLength(double polylineLength) {
        this.polylineLength = polylineLength;
    }

    public double getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(double currentDistance) {
        this.currentDistance = currentDistance;
    }

    public long getTimeSendInterval() {
        return timeSendInterval;
    }

    public void setTimeSendInterval(long timeSendInterval) {
        this.timeSendInterval = timeSendInterval;
    }

    public double getDistanceRegistrationInterval() {
        return distanceRegistrationInterval;
    }

    public void setDistanceRegistrationInterval(double distanceRegistrationInterval) {
        this.distanceRegistrationInterval = distanceRegistrationInterval;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(long currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public long getOngoingTime() {
        return ongoingTime;
    }

    public void setOngoingTime(long ongoingTime) {
        this.ongoingTime = ongoingTime;
    }

    public double getDistanceOnLastRegister() {
        return distanceOnLastRegister;
    }

    public void setDistanceOnLastRegister(double distanceOnLastRegister) {
        this.distanceOnLastRegister = distanceOnLastRegister;
    }

    public long getTimeOnLastRegister() {
        return timeOnLastRegister;
    }

    public void setTimeOnLastRegister(long timeOnLastRegister) {
        this.timeOnLastRegister = timeOnLastRegister;
    }
}
