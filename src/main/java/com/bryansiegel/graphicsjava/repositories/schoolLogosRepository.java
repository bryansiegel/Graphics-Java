package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.SchoolLogosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface schoolLogosRepository extends JpaRepository<SchoolLogosModel, Long> {
}
