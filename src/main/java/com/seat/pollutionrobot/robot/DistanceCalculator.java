package com.seat.pollutionrobot.robot;

public class DistanceCalculator {



    public double getActualDistance(double speed, double ongoingTime) {
        return speed * ongoingTime;
    }

    public double getPolylineDistance() {
        return 0;
    }
}
