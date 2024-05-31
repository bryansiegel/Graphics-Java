package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.IndexOfFormsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface indexOfFormsRepository extends JpaRepository<IndexOfFormsModel, Long> {
}
