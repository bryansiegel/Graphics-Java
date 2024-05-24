package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.DownloadsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface downloadsRepository extends JpaRepository<DownloadsModel, Long> {
}
