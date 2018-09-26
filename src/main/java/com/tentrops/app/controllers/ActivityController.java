package com.tentrops.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tentrops.app.models.entity.Activity;
import com.tentrops.app.models.entity.Sport;
import com.tentrops.app.models.service.definition.IActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private IActivityService activityService;
	
	/*
	 * '@ResponseBody' el resultado lo convierte en JSON y la guarda en la salida, en el response.
	 */
	@GetMapping(value="/load-sports/{term}", produces = {"application/json"})
	public @ResponseBody List<Sport> loadSports(@PathVariable String term) {
		return activityService.findByName(term);
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteActivity(@PathVariable(value="id") Long id, RedirectAttributes flash) {
			
		if(id > 0) {	
			Activity activity = activityService.findOne(id);			
			if (activity != null) {
				activityService.delete(id);
				flash.addFlashAttribute("success","The Activity Record was deleted successfully!");
			}
			else {
				flash.addFlashAttribute("error","The Activity doesn't exist in the database!");
			}
		}
		return "redirect:/user/dashboard";
	}
}