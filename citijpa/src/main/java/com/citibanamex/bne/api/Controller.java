package com.citibanamex.bne.api;

import java.util.List;
import java.util.Map;

import com.citibanamex.bne.jpa.serv;
import com.citibanamex.bne.jpa.usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

import com.citibanamex.bne.jdbcclient.model.SqlStatementRequest;
import com.citibanamex.bne.jdbcclient.model.SqlStatementResponse;
import com.citibanamex.bne.services.ApiService;
import com.citibanamex.bne.phrase.phraseapi;

@RestController
@RequestMapping("/v1")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ApiService apiService;

    @Autowired
    private serv servicio;
    
    @Autowired
    private phraseapi pr;
    
    @RequestMapping(value = "/hys")
    public String hom() {
    	return pr.home()+" I'm at ";
    }

    @RequestMapping(value = "/generic/endpoint1")
    public ResponseEntity<SqlStatementResponse> directQuery(@RequestBody SqlStatementRequest request) {
        SqlStatementResponse output = apiService.directRawQuery(request);

        logger.info("Calling direct query service");

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @RequestMapping(value = "/generic/endpoint2", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> directQuery() {
        Map<String, Object> output = apiService.proxyQueryToMap();

        logger.info("Calling direct query to map service");

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @RequestMapping(value = "/saludo")
    public ResponseEntity<Object> saludo() {

        logger.info("Calling direct query service");

        return new ResponseEntity<>("Hola desde citiJPA", HttpStatus.OK);
    }

    @RequestMapping(value = "/api", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(RequestEntity<Object> request) {
        return servicio.mensaje();
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String guardar(@RequestBody usuario usr) {
        return servicio.guardar(usr);
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<usuario> all() {
        return servicio.findAll();
    }

    @RequestMapping(value = "/uno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public usuario one(RequestEntity<Object> request) {
        return servicio.porpersona(request);
    }

    @RequestMapping(value = "/borra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public usuario borrar(RequestEntity<Object> request) {
        return servicio.erase(request);
    }

}
