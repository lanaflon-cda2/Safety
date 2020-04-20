package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PhoneAlertUrl;
import com.example.SafetyAlerts.services.GetPhoneAlert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class RestControllerPhoneAlert {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);

    private final GetPhoneAlert getPhoneAlert;

    public RestControllerPhoneAlert(GetPhoneAlert getPhoneAlert) {
        this.getPhoneAlert = getPhoneAlert;
    }


    @GetMapping
    public List<PhoneAlertUrl> getPhoneAlert(String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("Parameter Station is missing");
            throw new Exception("Parameter : Station value, is necessary");
        } else {
            logger.info("Get Phones OK");
            return getPhoneAlert.getPhoneAlert(station);
        }
    }
}
