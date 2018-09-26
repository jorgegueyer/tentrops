package com.tentrops.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tentrops.app.models.dao.definition.IRegistryDao;
import com.tentrops.app.models.entity.Registry;
import com.tentrops.app.models.service.definition.IRegistryService;

@Service
public class RegistryServiceImpl implements IRegistryService {
	
	@Autowired
	private IRegistryDao registryDao;
	
	@Transactional(readOnly=true)
	public List<Registry> findAll() {
		return (List<Registry>) registryDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Registry findOne(long id) {
		return registryDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Registry Registry) {
		registryDao.save(Registry);		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		registryDao.deleteById(id);		
	}
	
	@Transactional(readOnly=true)
	public boolean existsById(Long id) {
		return registryDao.existsById(id);
	}

	@Override
	public Page<Registry> findAll(Pageable pageable) {
		return registryDao.findAll(pageable);
	}	
}
