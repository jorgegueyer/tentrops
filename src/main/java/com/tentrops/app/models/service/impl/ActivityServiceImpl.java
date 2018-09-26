package com.tentrops.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tentrops.app.models.dao.definition.IActivityDao;
import com.tentrops.app.models.dao.definition.ISportDao;
import com.tentrops.app.models.entity.Activity;
import com.tentrops.app.models.entity.Sport;
import com.tentrops.app.models.service.definition.IActivityService;

@Service
public class ActivityServiceImpl implements IActivityService {
	
	@Autowired
	private IActivityDao activityDao;
	
	@Autowired
	private ISportDao sportDao;

	@Override
	@Transactional(readOnly=true)
	public List<Activity> findAll() {
		return (List<Activity>) activityDao.findAll();
	}
	
	@Override
	public Page<Activity> findAll(Pageable pageable) {
		return activityDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Activity	findOne(long id) {
		return activityDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Activity activity) {
		activityDao.save(activity);		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		activityDao.deleteById(id);		
	}
	
	@Transactional(readOnly=true)
	public boolean existsById(Long id) {
		return activityDao.existsById(id);
	}
	
	@Override
	public List<Sport> findByName(String name) {
		return sportDao.findByNameLikeIgnoreCase("%" + name + "%");
	}	
	
	@Override
	@Transactional
	public Sport findSportById(Long id) {
		return sportDao.findById(id).orElse(null);
	}	
}
