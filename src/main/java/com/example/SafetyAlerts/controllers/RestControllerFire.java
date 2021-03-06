package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.PersonInfos;
import com.example.SafetyAlerts.services.FireService;
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

/**
 * Controleur de l'URL Firestation.
 * Cette url retourne la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
 * de pompiers la desservant. La liste inclue le nom, le numéro de téléphone, l'âge et les antécédents
 * médicaux (médicaments, posologie et allergies) de chaque personne.
 */

@RestController
@RequestMapping("/fire")
public class RestControllerFire {
    private static final Logger logger = LogManager.getLogger(RestControllerFire.class);
    public final FireService fireService;

    public RestControllerFire(FireService fireService) {
        this.fireService = fireService;
    }

    /**
     *
     * @param address : Parametre adresse d'une Firestation
     * @return : Retourne les information du service FireService
     * @throws Exception : Exception si parametres non renseignés
     */

    @GetMapping
    public Object getFire(String address) throws Exception {

        if (address.isEmpty()) {
            logger.error("Parameter Address is missing");
            throw new Exception("Parameter : Address value, is necessary");
        } else {
            logger.info("Get Address OK");
            List<PersonInfos> resultPersonInfos = fireService.getFire(address);

            MappingJacksonValue result = new MappingJacksonValue(resultPersonInfos);
            FilterProvider filter = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept("stationNumber", "firstName", "lastName", "phone", "age", "medics", "allergies"));
            result.setFilters(filter);

            return result;
        }
    }
}
