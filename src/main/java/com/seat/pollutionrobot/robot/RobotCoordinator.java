package com.seat.pollutionrobot.robot;

import com.seat.pollutionrobot.listener.ReportListener;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RobotCoordinator {

    @Inject
    private ReportListener reportListener;
    @Inject
    private RobotCalculator robotCalculator;

    public Robot newRobot(String polyline) {
        return new Robot(polyline, reportListener, robotCalculator);
    }

}
