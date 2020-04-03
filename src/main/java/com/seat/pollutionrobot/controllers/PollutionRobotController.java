package com.seat.pollutionrobot.controllers;

import com.seat.pollutionrobot.services.PollutionRobotServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PollutionRobotController {

    @Autowired
    private PollutionRobotServices pollutionRobotServices;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/newRobot/{polyline}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void newRobot(@PathVariable String polyline) {
        pollutionRobotServices.newRobot(polyline);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/startRobot", produces = MediaType.APPLICATION_JSON_VALUE)
    public void startRobot() {
        pollutionRobotServices.startRobot();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/stopRobot", produces = MediaType.APPLICATION_JSON_VALUE)
    public void stopRobot() {
        pollutionRobotServices.stopRobot();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getReport", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getReport() {
        pollutionRobotServices.getReport();
    }

//
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @PostMapping(value = "/")
//    public String uploadAssignmentsFile(@PathVariable String l, @RequestParam("") String s) {
//        return null;
//    }

}
