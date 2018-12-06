package com.esme.spring.faircorp.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findByName(String name) {
        String jpql = "select rm from Room rm where rm.name = :value";
        return em.createQuery(jpql, Room.class)
                .setParameter("value", name)
                .getResultList();
    }

    @Override
    public List<Light> findRoomLightById(Long id) {
        String jpql = "select rm.lOfLights from Room rm where rm.id = :value";
        return em.createQuery(jpql,Light.class)
                .setParameter("value", id)
                .getResultList();
    }
}
