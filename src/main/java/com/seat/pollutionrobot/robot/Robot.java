package com.seat.pollutionrobot.robot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.dto.InformationOutputDTO;
import com.seat.pollutionrobot.dto.LatLngDTO;
import com.seat.pollutionrobot.gps.FakeGPS;
import com.seat.pollutionrobot.particulate_matter_reader.ParticulateMatterReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Robot {

//    private RobotMemory m = new RobotMemory();

    private volatile boolean isRobotOn;

    private ParticulateMatterReader pmReader = new ParticulateMatterReader();

    private String polyline = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGuAD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkICmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^yVJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSMGBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iDq@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQKkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
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

    private long stopTimestamp;
    private double stopCurrentDistance;

    private List<Integer> registeredValuesSinceLastRead = new ArrayList<>();

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


    private void sendRegistersMeanIfNeeded() {
        long timeSinceLastSend = currentTimestamp - timeOnLastRegister;
        if (timeSinceLastSend >= timeSendInterval && timeOnLastRegister != 0) {
            doSendPollutionInformation();
            timeOnLastRegister = currentTimestamp;
        }
    }



    private void doPollutionRegistration() {
        distanceOnLastRegister = currentDistance;
        registeredValuesSinceLastRead.add(pmReader.getRead());
    }

    private void doSendPollutionInformation() {
        System.out.println("Cycle");
        FakeGPS fakeGPS = new FakeGPS(polyline, ongoingTime, speed);
        actualPosition = fakeGPS.getActualPosition();

        ObjectMapper om = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = om.writeValueAsString(getInformationOutputDTO());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);

        registeredValuesSinceLastRead = new ArrayList<>();
    }

    private InformationOutputDTO getInformationOutputDTO() {
        InformationOutputDTO informationOutputDTO = new InformationOutputDTO();
        LatLngDTO latLngDto = new LatLngDTO();
        latLngDto.setLat(actualPosition.lat);
        latLngDto.setLng(actualPosition.lng);

        informationOutputDTO.setTimestamp(new Date().getTime());
        informationOutputDTO.setLocation(latLngDto);
        informationOutputDTO.setLevel(getPollutionValuesMeanRange());
        informationOutputDTO.setSource("Robot");

        return informationOutputDTO;
    }

    private String getPollutionValuesMeanRange() {
        int mean = getPollutionValuesMean();

        if (mean == -1) {
            return "Empty";
        }

        if (mean < 50) {
            return "Good";
        } else if (mean < 100) {
            return "Moderate";
        } else if (mean < 150) {
            return "USG";
        } else {
            return "Unhealthy";
        }
    }
    private int getPollutionValuesMean() {
        int registeredValuesSum = 0;
        for (int value : registeredValuesSinceLastRead) {
            registeredValuesSum += value;
        }
        if (registeredValuesSinceLastRead.size() == 0) {
            return -1;
        }
        return registeredValuesSum / registeredValuesSinceLastRead.size();
    }

}
