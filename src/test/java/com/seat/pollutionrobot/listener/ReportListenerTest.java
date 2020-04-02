package com.seat.pollutionrobot.listener;

import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.entities.RobotReport;
import com.seat.pollutionrobot.publisher.ReportPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReportListener.class })
public class ReportListenerTest {

    @Test
    public void coverReportListenerCallsToPublisher() throws Exception {
        ReportPublisher reportPublisher = Mockito.mock(ReportPublisher.class);

        PowerMockito.whenNew(ReportPublisher.class).withNoArguments().thenReturn(reportPublisher);

        ReportListener reportListener = new ReportListener();

        RobotReport robotReport = new RobotReport();
        robotReport.setLocation(new LatLng());
        reportListener.publishRobotReport(robotReport);

        Mockito.verify(reportPublisher, Mockito.times(1)).consoleLogRobotReportJSON(any());

    }

}