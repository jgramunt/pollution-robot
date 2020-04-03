package com.seat.pollutionrobot.listener;

import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.entities.RobotReport;
import com.seat.pollutionrobot.publisher.ReportPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReportListener.class })
public class ReportListenerTest {

    @Mock
    ReportPublisher reportPublisher;

    @InjectMocks
    ReportListener reportListener;

    @Test
    public void coverReportListenerCallsToPublisher() throws Exception {
        PowerMockito.whenNew(ReportPublisher.class).withNoArguments().thenReturn(reportPublisher);

        RobotReport robotReport = new RobotReport();
        robotReport.setTimestamp(123456789);
        robotReport.setLocation(new LatLng());
        robotReport.setLevel("test");
        robotReport.setSource("robot-test");
        reportListener.publishRobotReport(robotReport);

        Mockito.verify(reportPublisher, Mockito.times(1)).consoleLogRobotReportJSON(any());

    }

}