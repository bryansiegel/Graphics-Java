package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.FormDownloadsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface formDownloadsRepository extends JpaRepository<FormDownloadsModel, Long> {
}
