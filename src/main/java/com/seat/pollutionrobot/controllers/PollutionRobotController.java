package com.seat.pollutionrobot.controllers;

import com.seat.pollutionrobot.services.PollutionRobotServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PollutionRobotController {

    @Autowired
    private PollutionRobotServices pollutionRobotServices;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/questions/{languageCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getResultsByLanguage(@PathVariable String languageCode) throws IOException {
        return null;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/uploadFile/{languageCode}")
    public String uploadAssignmentsFile(@PathVariable String languageCode, @RequestParam("file") MultipartFile xlsxFile) {
        return null;
    }

}
