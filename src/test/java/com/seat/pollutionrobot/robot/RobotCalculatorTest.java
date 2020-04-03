package com.seat.pollutionrobot.robot;

import com.seat.pollutionrobot.robot.utils.RobotCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
public class RobotCalculatorTest {

    RobotCalculator robotCalculator = new RobotCalculator();

    @Test
    public void getRegisteredValuesMean() {
        List<Integer> registeredValuesSinceLastRead = new ArrayList<>();
        registeredValuesSinceLastRead.add(2);
        registeredValuesSinceLastRead.add(4);

        assertEquals(3, robotCalculator.getPollutionValuesMean(registeredValuesSinceLastRead));
    }

    @Test
    public void getMinusOneIfListIsEmpty() {
        List<Integer> registeredValuesSinceLastRead = new ArrayList<>();
        assertEquals(-1, robotCalculator.getPollutionValuesMean(registeredValuesSinceLastRead));
    }

    @Test
    public void getProperStringsForEachValue() {
        RobotCalculator spy = Mockito.spy(new RobotCalculator());
        Mockito.doReturn(-1)
                .doReturn(25)
                .doReturn(75)
                .doReturn(125)
                .doReturn(175)
                .when(spy).getPollutionValuesMean(any());

        assertEquals("Empty", spy.getPollutionValuesMeanRange(any()));
        assertEquals("Good", spy.getPollutionValuesMeanRange(any()));
        assertEquals("Moderate", spy.getPollutionValuesMeanRange(any()));
        assertEquals("USG", spy.getPollutionValuesMeanRange(any()));
        assertEquals("Unhealthy", spy.getPollutionValuesMeanRange(any()));
    }

}