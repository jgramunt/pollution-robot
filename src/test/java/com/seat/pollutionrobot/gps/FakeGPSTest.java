package com.seat.pollutionrobot.gps;

import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FakeGPSTest {

    private String polyline;

    @Before
    public void init() {
        polyline = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGuAD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkICmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^yVJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSMGBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iDq@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQKkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
    }

    @Test
    public void fakeGPSWithZeroOngoingTimeShouldReturnAsPositionTheFirstPointOfPolyline() {

        FakeGPS fakeGPS = new FakeGPS(polyline, 0, 2);

        LatLng currentLatLng = fakeGPS.getActualPosition();
        int currentLat = (int) (currentLatLng.lat * 100000);
        int currentLng = (int) (currentLatLng.lng * 100000);

        assertEquals(5150487, currentLat);
        assertEquals(-21533, currentLng);

    }

    @Test
    public void fakeGPSWithMoreTimeReturnsDiferentPosition() {

        FakeGPS fakeGPS = new FakeGPS(polyline, 100000, 2);

        LatLng currentLatLng = fakeGPS.getActualPosition();
        int currentLat = (int) (currentLatLng.lat * 100000);
        int currentLng = (int) (currentLatLng.lng * 100000);

        assertNotEquals(5150487, currentLat);
        assertNotEquals(-21533, currentLng);

    }

    @Test
    public void fakeGPSReturnsValidDistance() {
        FakeGPS fakeGPS = new FakeGPS(polyline, 0, 2);

        assertEquals(11126, (int) fakeGPS.getPolylineLength());
    }

}