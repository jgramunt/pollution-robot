package com.seat.pollutionrobot.entities;

import com.google.maps.model.LatLng;
import com.seat.pollutionrobot.json.LatLngJSON;

public class RobotReport {

    private long timestamp;
    private LatLng location;
    private String level;
    private String source;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
