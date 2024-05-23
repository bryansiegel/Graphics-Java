package com.bryansiegel.graphicsjava.repositories;

import com.bryansiegel.graphicsjava.models.SiteBasedContractsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface siteBasedContractsRepository extends JpaRepository<SiteBasedContractsModel, Long> {
}
