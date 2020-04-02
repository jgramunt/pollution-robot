package com.seat.pollutionrobot.listener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.pollutionrobot.entities.RobotReport;
import com.seat.pollutionrobot.json.RobotReportJSON;
import com.seat.pollutionrobot.json.LatLngJSON;
import com.seat.pollutionrobot.publisher.ReportPublisher;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


@Named
public class ReportListener {

    @Inject
    private ReportPublisher reportPublisher;

    ObjectMapper om = new ObjectMapper();


    public void publishRobotReport(RobotReport robotReport) {
        String jsonString = convertRobotReportToJSON(robotReport);
        reportPublisher.consoleLogRobotReportJSON(jsonString);
    }

    private String convertRobotReportToJSON(RobotReport robotReport) {

        LatLngJSON latLngJSON = new LatLngJSON();
        latLngJSON.setLat(robotReport.getLocation().lat);
        latLngJSON.setLng(robotReport.getLocation().lng);

        RobotReportJSON robotReportJSON = new RobotReportJSON();
        robotReportJSON.setTimestamp(robotReport.getTimestamp());
        robotReportJSON.setLocation(latLngJSON);
        robotReportJSON.setLevel(robotReport.getLevel());
        robotReportJSON.setSource(robotReport.getSource());

        String jsonString = null;

        try {
            jsonString = om.writeValueAsString(robotReportJSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

}
