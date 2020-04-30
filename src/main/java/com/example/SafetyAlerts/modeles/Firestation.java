package com.example.SafetyAlerts.modeles;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.stereotype.Component;

@Component
public class Firestation {

    private String address;
    private String station;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }


}
