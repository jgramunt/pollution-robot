package com.seat.pollutionrobot.particulate_matter_reader;

import java.util.Random;

public class ParticulateMatterReader {

    public int getRead() {
        Random random = new Random();
        return random.nextInt(200);
    }

}
