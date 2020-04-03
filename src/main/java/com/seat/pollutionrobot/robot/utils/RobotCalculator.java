package com.seat.pollutionrobot.robot.utils;

import javax.inject.Named;
import java.util.List;

@Named
public class RobotCalculator {

    public String getPollutionValuesMeanRange(List<Integer> registeredValuesSinceLastRead) {
        int mean = getPollutionValuesMean(registeredValuesSinceLastRead);

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

    public int getPollutionValuesMean(List<Integer> registeredValuesSinceLastRead) {
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
