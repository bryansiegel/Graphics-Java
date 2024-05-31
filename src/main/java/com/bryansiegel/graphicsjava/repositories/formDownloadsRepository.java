package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.FormDownloadsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface formDownloadsRepository extends JpaRepository<FormDownloadsModel, Long> {
}
