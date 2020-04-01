package com.seat.pollutionrobot.robot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

    @Test
    public void testRobot() {
        System.out.println("Hello!");
        Robot robot = new Robot();
        robot.start();
    }
}