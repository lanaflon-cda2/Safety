package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.IGetCommunityEmailInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communityEmail")
public class RestControllerPersonEmail {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);

    private final IGetCommunityEmailInfo igetCommunityEmailInfo;
    public RestControllerPersonEmail(IGetCommunityEmailInfo igetCommunityEmailInfo) {
        this.igetCommunityEmailInfo = igetCommunityEmailInfo;
    }


    @GetMapping
    public Object getEmail(@RequestParam("city") String city) throws Exception {
        if (city.isEmpty()) {
            logger.error("Parameter City is necessary");
            throw new Exception("Parameter : city value, is necessary");
        } else {
            logger.info("Get Emails OK");
            return igetCommunityEmailInfo.getEmail(city);
        }
    }
}
