package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.DownloadsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface downloadsRepository extends JpaRepository<DownloadsModel, Long> {
}
