package com.seat.pollutionrobot.publisher;

import javax.inject.Named;

@Named
public class ReportPublisher {

    public void consoleLogRobotReportJSON(String robotReportJsonAsString) {
        System.out.println(robotReportJsonAsString);
    }

}
