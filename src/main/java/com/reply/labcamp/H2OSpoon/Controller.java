package com.reply.labcamp.H2OSpoon;

import com.reply.labcamp.H2OSpoon.data.C6H6;
import com.reply.labcamp.H2OSpoon.data.PTO8;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
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
    @Operation(description = "Returns a list of the value of Benzene for the last 48 hours")
    public ResponseEntity<Map<String, Double>> getBenzene48hours(){
        return ResponseEntity.ok(benzene.listBenzeneValues());
    }

    @GetMapping("titanium")
    @Operation(description = "Returns a list of the value of Titanium for the last 48 hours")
    public ResponseEntity<Map<String, Double>> getTitania48Hours(){
        return ResponseEntity.ok(titania.litsOtherValues());
    }

    @GetMapping("benzene/{lagi}")
    @Operation(description = "Returns the value of Benzene measured lagi hours ago")
    public ResponseEntity<Double> getBenzeneLag(@PathVariable("lagi") String lagi){
        Map<String, Double> map = benzene.listBenzeneValues();
        logger.debug(map.toString());
        String key = "lag"+lagi;
        if(map.containsKey(key)){
            return ResponseEntity.ok(map.get(key));
        }else {
            throw new IllegalArgumentException("lag not found, please select from lag1 to lag48");
        }
    }

    @GetMapping("titanium/{lagi}")
    @Operation(description = "Returns the value of Titanium measured lagi hours ago")
    public ResponseEntity<Double> getTitaniumLag(@PathVariable("lagi") String lagNumber){
        Map<String, Double> map = titania.litsOtherValues();
        String key = "lag"+lagNumber;
        if(map.containsKey(key)){
            return ResponseEntity.ok(map.get(key));
        }else {
            throw new IllegalArgumentException("lag not found, please select from lag1 to lag48");
        }
    }


}
