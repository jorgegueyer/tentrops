package com.tentrops.app.models.service.definition;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tentrops.app.models.entity.Activity;
import com.tentrops.app.models.entity.Sport;


public interface IActivityService {

	public List<Activity> findAll();
	
	public Page<Activity> findAll(Pageable pageable);
	
	public Activity findOne(long id);
	
	public void save(Activity user);
	
	public void delete(Long id);
	
	public boolean existsById(Long id);
	
	public List<Sport> findByName(String name);
	
	public Sport findSportById(Long id);
}
