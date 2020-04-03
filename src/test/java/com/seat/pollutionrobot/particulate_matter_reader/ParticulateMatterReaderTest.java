package com.seat.pollutionrobot.particulate_matter_reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ParticulateMatterReaderTest {

    @Test
    public void assertParticulateMatterReaderValuesAreBetweenZeroAndTwoHundred() {
        ParticulateMatterReader particulateMatterReader = new ParticulateMatterReader();
        int read1 = particulateMatterReader.getRead();
        int read2 = particulateMatterReader.getRead();
        int read3 = particulateMatterReader.getRead();
        int read4 = particulateMatterReader.getRead();
        int read5 = particulateMatterReader.getRead();

        assertTrue(read1 >= 0 && read1 < 200);
        assertTrue(read2 >= 0 && read2 < 200);
        assertTrue(read3 >= 0 && read3 < 200);
        assertTrue(read4 >= 0 && read4 < 200);
        assertTrue(read5 >= 0 && read5 < 200);
    }

}