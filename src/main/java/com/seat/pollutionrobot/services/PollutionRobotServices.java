package com.seat.pollutionrobot.services;

import com.seat.pollutionrobot.publisher.ReportPublisher;
import com.seat.pollutionrobot.robot.Robot;
import com.seat.pollutionrobot.robot.utils.RobotCoordinator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class PollutionRobotServices {

    @Inject
    RobotCoordinator robotCoordinator;

    @Inject
    ReportPublisher reportPublisher;

    Robot robot;

    public void newRobot(String polyline) {
        reportPublisher.consoleLogRobotReportJSON("Robot created");
        robot = robotCoordinator.newRobot(polyline);
    }

    public void startRobot() {
        if (robot != null) {
            reportPublisher.consoleLogRobotReportJSON("Robot started");
            robot.start();
        } else {
            reportPublisher.consoleLogRobotReportJSON("No robot has been started");
        }
    }

    public void stopRobot() {
        if (robot != null) {
            robot.stop();
            reportPublisher.consoleLogRobotReportJSON("Robot stopped");
        } else {
            reportPublisher.consoleLogRobotReportJSON("No robot has been started");
        }
    }

    public void getReport() {
        if (robot != null) {
            robot.doSendPollutionInformation();
        } else {
            reportPublisher.consoleLogRobotReportJSON("No robot has been started");
        }
    }

}
