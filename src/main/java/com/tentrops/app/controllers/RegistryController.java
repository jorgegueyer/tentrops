package com.tentrops.app.controllers;

import java.time.LocalTime;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tentrops.app.models.entity.Activity;
import com.tentrops.app.models.entity.Registry;
import com.tentrops.app.models.entity.Sport;
import com.tentrops.app.models.service.definition.IActivityService;
import com.tentrops.app.models.service.definition.IRegistryService;

@Controller
@RequestMapping("/registry")
@SessionAttributes("registry") 
public class RegistryController {
	
	@Autowired 
	private IRegistryService registryService;
	
	@Autowired 
	private IActivityService activityService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="/details/{id}")
	public String see(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Registry registry = registryService.findOne(id);		
		if (registry == null) {
			flash.addFlashAttribute("error", "The Activity Record Details doesn't exist in the Data Base!");
			return "redirect:/user/dashboard";
		}
		
		log.info("Registry Id: " + registry.getId().toString());
		
		model.put("registry", registry);
		model.put("title", "Record Details");
		return "/registry/details";
	}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable(value="id") Long id, Map<String,Object> model, RedirectAttributes flash) {
		Registry registry = null;
		if (id > 0) {
			registry = registryService.findOne(id);
			if (registry == null) {
				flash.addFlashAttribute("error","The Activity Record doesn't exist in the BBDD!");
				return "redirect:/user/dashboard";
			}
		}
		else {
			flash.addFlashAttribute("error","The Activity Record doesn't exist in the BBDD!");
			return "redirect:/user/dashboard";
		}
		model.put("title", "Edit Record");
		model.put("registry", registry);
		return "registry/edit";
	}	
	
	@PostMapping("/edit/save")
	public String update(@Valid Registry registry, BindingResult result,
			@RequestParam(name="item_id[]", required=false) Long[] sportId,
			@RequestParam(name="time[]", required=false) String[] duration,
			@RequestParam(name="distante[]", required=false) Double[] distance,
			Model model, RedirectAttributes flash,
			SessionStatus status) {	
		
		log.info("Registry ID: " + registry.getId().toString());
		
		if(result.hasErrors()) {
			model.addAttribute("title","Edit Record");
			return "registry/edit";
		}
		
		if(sportId == null || sportId.length == 0) {
			model.addAttribute("title","Edit Record");
			model.addAttribute("error","Error: The Registry must have one Activity at least!");
			return "registry/edit";
		}
		
		for (int i = 0; i < sportId.length; i++) {
			log.info(registry.getActivities().toString());
			Sport sport = activityService.findSportById(sportId[i]);
			Activity act = new Activity();
			log.info("Sport ID: " + sportId[i].toString());						
			act.setDuration(LocalTime.parse(duration[i]));
			log.info(sport.getName() + " : " + act.getDurationRepresentation());
			act.setDistance(distance[i]);
			act.setSport(sport);
			registry.addActivity(act);
		}
		registryService.save(registry);
		status.setComplete();
		flash.addFlashAttribute("succes", "Activity was created successfully!");
		return "redirect:/registry/details/" + registry.getId();
	}
	
	@GetMapping(value="/form") 
	public String create(Map<String,Object> model ) {
		Registry registry = new Registry();
		model.put("registry",registry);
		model.put("title", "Activity Record Form");		
		return "registry/form";
	}
	
	@PostMapping(value="/form/save")
	public String save(@Valid Registry registry, BindingResult result, RedirectAttributes flash, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("title","Activity Record Form");
			return "registry/form";
		}
		
		log.info("Registry Id: " + registry.getId().toString());
		
		if (!registryService.existsById(registry.getId())) {
			String message = (registry.getId() > 0) ? "The Activity Record was edited succesfully!" : "The Activity Record was created successfully!";
			registryService.save(registry);
			status.setComplete(); 
			flash.addFlashAttribute("success",message);
		}	
		else {
			model.addAttribute("title","Activity Record Form");
			flash.addFlashAttribute("error","That date has already had one registry associated!");
			return "redirect:/user/dashboard";
		}	
		
		return "redirect:/user/dashboard";
	}		
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {	
			Registry registry = registryService.findOne(id);			
			if (registry != null) {
				registryService.delete(id);
				flash.addFlashAttribute("success","The Registry Record was deleted successfully!");
			}
			else {
				flash.addFlashAttribute("error","The Registry doesn't exist in the database!");
			}
		}
		return "redirect:/user/dashboard";
	}
}
