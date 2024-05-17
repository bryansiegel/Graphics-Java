package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.CurrentEvaluationsModel;
import org.springframework.data.repository.CrudRepository;

public interface currentEvaluationsRepository extends CrudRepository<CurrentEvaluationsModel, Long> {
}
