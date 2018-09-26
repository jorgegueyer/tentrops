package com.tentrops.app.models.dao.definition;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tentrops.app.models.entity.Sport;

public interface ISportDao extends CrudRepository<Sport, Long> {
	
	@Query("SELECT p FROM Sport p WHERE p.name LIKE %?1%")
	public List<Sport> findByName(String name);
	
	/*
	 * Query creation with the method name (Spring Data)
	 */
	public List<Sport> findByNameLikeIgnoreCase(String name);
}
