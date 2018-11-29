package com.citibanamex.bne.jpa;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class repo {

    @Autowired
    private EntityManager en;

    public String mensaje() {
        return "Hola desde repositorio";
    }

    public String guardar(usuario usuario) {
        en.persist(usuario);
        return "Implementacion exitosa";
    }

    @SuppressWarnings("unchecked")
    public List<usuario> getAll() {
        Query query = en.createQuery("SELECT u FROM usuario u");
        List<usuario> usuarios = query.getResultList();
        return usuarios;
    }

    @SuppressAjWarnings("unchecked")
    public usuario findbyId(RequestEntity<Object> request) {
        Map<String, Object> mapBody = new HashMap<String, Object>();

        mapBody = (Map<String, Object>) request.getBody();

        Query query = en.createQuery("FROM usuario WHERE id=:id").setParameter("id", mapBody.get("id"));
        query.getSingleResult();

        usuario usr = (usuario) query.getSingleResult();
        return usr;
    }

    @SuppressAjWarnings("unchecked")
    public usuario deletebyId(RequestEntity<Object> request) {
        Map<String, Object> mapBody = new HashMap<String, Object>();

        mapBody = (Map<String, Object>) request.getBody();

        Query query = en.createQuery("DELETE FROM usuario u WHERE u.id=:id").setParameter("id", mapBody.get("id"));
        query.getSingleResult();

        usuario usr = (usuario) query.getSingleResult();
        return usr;
    }

}
