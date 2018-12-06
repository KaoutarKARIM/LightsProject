package com.esme.spring.faircorp.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BuildingDaoImpl implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;


    @Override
    public List findLightsByBuildingId(Long id) {
        String jpql = "select rm.lOfLights from Room rm join rm.building bd where bd.id = :value";
        return em.createQuery(jpql)
                .setParameter("value", id)
                .getResultList();
    }
}
