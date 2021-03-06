package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PersonInfos;
import com.example.SafetyAlerts.services.FloodService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Controleur de l'URL flood :
 * Cette url retourne une liste de tous les foyers desservis par la caserne. Cette liste regroupe les
 * personnes par adresse. Elle inclue le nom, le numéro de téléphone et l'âge des habitants, et
 * fais figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
 */

@RestController
@RequestMapping("/flood")
public class RestControllerFlood {
    private static final Logger logger = LogManager.getLogger(RestControllerFlood.class);
    private final FloodService floodService;

    public RestControllerFlood(FloodService floodService) {
        this.floodService = floodService;
    }

    /**
     *
     * @param station : Numero de Firestaiton
     * @return : Retourne les informations du Service FloodService
     * @throws Exception : Exception si parametres non renseignés
     */

    @GetMapping
    public Object getFlood(String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("Parameter Station is missing");
            throw new Exception("Parameter : Station value, is necessary");
        } else {
            logger.info("Get Persons from Station OK");
            List<PersonInfos> resultPersonInfos = floodService.getFlood(station);

            MappingJacksonValue result = new MappingJacksonValue(resultPersonInfos);
            FilterProvider filter = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept("stationNumber", "firstName", "lastName", "address", "phone", "age","medics", "allergies"));
            result.setFilters(filter);

            return result;
        }
    }
}
