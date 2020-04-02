package com.seat.pollutionrobot.robot;

import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.entities.RobotReport;
import com.seat.pollutionrobot.json.RobotReportJSON;
import com.seat.pollutionrobot.json.LatLngJSON;
import com.seat.pollutionrobot.gps.FakeGPS;
import com.seat.pollutionrobot.listener.ReportListener;
import com.seat.pollutionrobot.particulate_matter_reader.ParticulateMatterReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Robot {

//    private RobotMemory m = new RobotMemory();

    private volatile boolean isRobotOn;

    private ParticulateMatterReader pmReader = new ParticulateMatterReader();
    private RobotCalculator robotCalculator = new RobotCalculator();

    private String polyline;
    private List<LatLng> polylinePointList;
    private LatLng actualPosition;
    private double polylineLength;
    private double currentDistance;

    private long timeSendInterval = 1000; //15 minutes in milliseconds = 900000
    private double distanceRegistrationInterval = 10; //in meters
    private double speed = 2;

    private long startTimestamp;
    private long currentTimestamp;
    private long ongoingTime;

    private double distanceOnLastRegister;
    private long timeOnLastRegister = new Date().getTime();

    private List<Integer> registeredValuesSinceLastRead = new ArrayList<>();

    // Reporta las posiciones cada 100 metros
//    @Autowired
//    private positionListener positionListener;

    private ReportListener reportListener = new ReportListener();

    public Robot(String polyline) {
        this.polyline = polyline;
    }


    public void start() {
        startTimestamp = new Date().getTime();
        FakeGPS fakeGPS = new FakeGPS(polyline, ongoingTime, speed);
        polylineLength = fakeGPS.getPolylineLength();
        isRobotOn = true;
        robotIsOn();
    }

    public void stop() {
        isRobotOn = false;
    }

    public void setSpeed(double speed) {
        if (speed < 1 || speed > 3) {
            //TODO throw Exception so User can see if speed is not correct
        } else {
            this.speed = speed;
        }
    }


    private void robotIsOn() {
        while (isRobotOn) {
            updateTime();
            updateCurrentDistance();

            stopIfPolylineHasEnded();
            makeRegistrationIfNeeded();
            sendRegistersMeanIfNeeded();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    private void updateCurrentDistance() {
        currentDistance = speed * (ongoingTime / 1000);

    }

    private void makeRegistrationIfNeeded() {
        double distanceSinceLastRegister = currentDistance - distanceOnLastRegister;
        if (distanceSinceLastRegister >= distanceRegistrationInterval) {
            doPollutionRegistration();
            distanceOnLastRegister = currentDistance;
        }
    }

    private void doPollutionRegistration() {
        distanceOnLastRegister = currentDistance;
        registeredValuesSinceLastRead.add(pmReader.getRead());
    }


    private void sendRegistersMeanIfNeeded() {
        long timeSinceLastSend = currentTimestamp - timeOnLastRegister;
        if (timeSinceLastSend >= timeSendInterval && timeOnLastRegister != 0) {
            doSendPollutionInformation();
            timeOnLastRegister = currentTimestamp;
        }
    }

    private void doSendPollutionInformation() {
        FakeGPS fakeGPS = new FakeGPS(polyline, ongoingTime, speed);
        actualPosition = fakeGPS.getActualPosition();

        RobotReport robotReport = new RobotReport();
        robotReport.setTimestamp(new Date().getTime());
        robotReport.setLocation(actualPosition);
        robotReport.setLevel(robotCalculator.getPollutionValuesMeanRange(registeredValuesSinceLastRead));
        robotReport.setSource("robot");

        reportListener.publishRobotReport(robotReport);
        registeredValuesSinceLastRead = new ArrayList<>();
    }

}
