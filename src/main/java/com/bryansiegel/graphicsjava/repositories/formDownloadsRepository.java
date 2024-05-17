package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.FormDownloadsModel;
import org.springframework.data.repository.CrudRepository;

public interface formDownloadsRepository extends CrudRepository<FormDownloadsModel, Long> {
}
