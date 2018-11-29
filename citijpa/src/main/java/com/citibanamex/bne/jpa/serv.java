package com.citibanamex.bne.jpa;

import org.springframework.http.RequestEntity;

import java.util.List;

public interface serv {

    public String mensaje();

    public String guardar(usuario usr);

    public List<usuario> findAll();

    public usuario porpersona(RequestEntity<Object> request);

    public usuario erase(RequestEntity<Object> request);

}
