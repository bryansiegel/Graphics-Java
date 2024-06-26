package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.WorkRequestsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface workRequestsRepository extends JpaRepository<WorkRequestsModel, Long> {
}
