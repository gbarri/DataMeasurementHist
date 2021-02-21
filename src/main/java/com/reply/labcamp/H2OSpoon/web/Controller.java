package com.reply.labcamp.H2OSpoon.web;

import com.reply.labcamp.H2OSpoon.data.C6H6;
import com.reply.labcamp.H2OSpoon.data.PTO8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "api/", produces = {MediaType.APPLICATION_JSON_VALUE,
        "application/hal+json", MediaType.APPLICATION_JSON_VALUE})
public class Controller {

    static Logger logger = LoggerFactory.getLogger("Controller");

    @Autowired
    C6H6 benzene;

    @Autowired
    PTO8 titania;

    @GetMapping("benzene")
    public Map<String, Double> getBenzene48hours(){
        return benzene.listBenzeneValues();
    }

    @GetMapping("titania")
    public Map<String, Double> getTitania48Hours(){
        return titania.litsOtherValues();
    }

}
