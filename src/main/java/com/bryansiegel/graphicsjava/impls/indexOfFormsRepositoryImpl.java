package com.bryansiegel.graphicsjava.impls;

import com.bryansiegel.graphicsjava.models.IndexOfFormsModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class indexOfFormsRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<IndexOfFormsModel> latestFiveResults(int limit) {
        return em.createQuery("SELECT p FROM IndexOfFormsModel p ORDER BY p.id LIMIT 5",
                IndexOfFormsModel.class).setMaxResults(limit).getResultList();
    }
}
