package com.seat.pollutionrobot;

import com.seat.pollutionrobot.robot.Robot;
import com.seat.pollutionrobot.robot.RobotCoordinator;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {

    //TODO check if needed

    @Inject
    private RobotCoordinator robotCoordinator;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
