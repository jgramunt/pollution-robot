package com.seat.pollutionrobot.robot.utils;

import com.seat.pollutionrobot.listener.ReportListener;
import com.seat.pollutionrobot.robot.Robot;

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
