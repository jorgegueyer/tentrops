package com.tentrops.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tentrops.app.models.entity.Registry;
import com.tentrops.app.models.service.definition.IRegistryService;
import com.tentrops.app.utils.pagination.PageRender;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
	
	@Autowired 
	private IRegistryService registryService;

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String listRegistry(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Registry> registries = registryService.findAll(pageRequest);
		
		PageRender<Registry> pageRender = new PageRender<Registry>("/dashboard", registries);
		
		model.addAttribute("title","Records List");
		model.addAttribute("registries",registries);
		model.addAttribute("page", pageRender);
		return "dashboard";
	}
}
