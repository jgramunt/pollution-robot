package com.seat.pollutionrobot.robot;

import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.gps.GPS;
import com.seat.pollutionrobot.gps.impl.FakeGPS;

import java.util.Date;

public class Robot {

    private RobotMemory m = new RobotMemory();

    public void start() {
        m.startTimestamp = new Date().getTime();
        m.isRobotOn = true;
        robotIsOn();
    }

    public void stop() {
        m.isRobotOn = false;
    }

    public LatLng getActualPosition() {
        GPS fakeGPS = new FakeGPS(m);
        return fakeGPS.getLocation();
    }

    public void setPolyline(String polyline) {
        m.polyline = polyline;
    }

    public double getCurrentDistance() {
        return Double.parseDouble(null);
    }

    public void setSpeed(double speed) {
        if (speed < 1 || speed > 3) {
            //TODO throw Exception so User can see a message
        } else {
            m.speed = speed;
        }
    }



    private void robotIsOn() {
        while (m.isRobotOn) {
            updateTime();
            updateActualDistance();

            stopIfPolylineHasEnded();
            makeRegistrationIfNeeded();
            sendRegistersMeanIfNeeded();
        }
    }

    private void stopIfPolylineHasEnded() {
        if (m.currentDistance >= m.polylineLength) {
            stop();
        }
    }


    private void updateTime() {
        m.currentTimestamp = new Date().getTime();
        m.ongoingTime = m.currentTimestamp - m.startTimestamp;
    }

    private void updateActualDistance() {
        m.currentDistance = m.speed * m.ongoingTime;
    }

    private void makeRegistrationIfNeeded() {
        double distanceSinceLastRegister = m.currentDistance - m.distanceOnLastRegister;
        if (distanceSinceLastRegister >= m.distanceRegistrationInterval) {
            doPollutionRegistration();
            m.distanceOnLastRegister = m.currentDistance;
        }
    }


    private void sendRegistersMeanIfNeeded() {
        long timeSinceLastSend = m.currentTimestamp - m.timeOnLastRegister;
        if (timeSinceLastSend >= m.timeSendInterval) {
            doSendPollutionInformation();
            m.timeOnLastRegister = m.currentTimestamp;
        }
    }



    private void doPollutionRegistration() {
        //TODO pollution registration
    }
    private void doSendPollutionInformation() {
        //TODO send pollution information
    }

}
