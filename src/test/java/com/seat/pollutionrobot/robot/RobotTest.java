package com.seat.pollutionrobot.robot;

import com.seat.pollutionrobot.listener.ReportListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
public class RobotTest {

    private String polyline;

    private RobotCalculator robotCalculator = Mockito.mock(RobotCalculator.class);
    private ReportListener reportListener = Mockito.mock(ReportListener.class);

    @Before
    public void init() {
        polyline = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGuAD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkICmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^yVJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSMGBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iDq@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQKkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
    }

    @Test
    public void checkIfRobotIsOnAndOff() throws InterruptedException {
        List<Boolean> onAndOff = new ArrayList();
        Robot robot = new Robot(polyline, reportListener, robotCalculator);
        onAndOff.add(robot.isRobotOn());

        Executors.newSingleThreadExecutor().execute(() -> robot.start());
        Thread.sleep(500);
        onAndOff.add(robot.isRobotOn());
        robot.stop();
        onAndOff.add(robot.isRobotOn());

        assertFalse(onAndOff.get(0));
        assertTrue(onAndOff.get(1));
        assertFalse(onAndOff.get(2));
    }

    @Test
    public void testRobot() {
//        robotCalculator = Mockito.mock(RobotCalculator.class);
//        reportListener = Mockito.mock(ReportListener.class);
        Mockito.when(robotCalculator.getPollutionValuesMeanRange(any())).thenReturn("test");

        Robot robot = new Robot(polyline, reportListener, robotCalculator);
        robot.start();
    }


}