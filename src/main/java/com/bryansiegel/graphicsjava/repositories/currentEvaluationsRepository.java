package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.CurrentEvaluationsModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface currentEvaluationsRepository extends JpaRepository<CurrentEvaluationsModel, Long> {
}
