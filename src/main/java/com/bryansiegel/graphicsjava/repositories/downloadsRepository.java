package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.DownloadsModel;
import org.springframework.data.repository.CrudRepository;

public interface downloadsRepository extends CrudRepository<DownloadsModel, Long> {
}
