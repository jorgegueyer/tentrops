package com.tentrops.app.models.service.definition;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tentrops.app.models.entity.Registry;


public interface IRegistryService {

	public List<Registry> findAll();
	
	public Page<Registry> findAll(Pageable pageable);
	
	public Registry findOne(long id);
	
	public void save(Registry user);
	
	public void delete(Long id);
	
	public boolean existsById(Long id);
}
