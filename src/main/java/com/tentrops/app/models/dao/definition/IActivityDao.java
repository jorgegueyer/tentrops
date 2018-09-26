package com.tentrops.app.models.dao.definition;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tentrops.app.models.entity.Activity;

public interface IActivityDao extends PagingAndSortingRepository<Activity, Long> {}
