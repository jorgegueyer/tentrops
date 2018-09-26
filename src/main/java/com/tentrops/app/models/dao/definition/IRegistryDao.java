package com.tentrops.app.models.dao.definition;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tentrops.app.models.entity.Registry;

public interface IRegistryDao extends PagingAndSortingRepository<Registry, Long> {}