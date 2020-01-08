package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.service.ISalleService;

@RestController
public class SalleController {
	
	@Autowired
	private ISalleService salleService;
	
	@GetMapping(path = "/salle")
	public List<SalleDto> getAll(){
		return this.salleService.findAll();
	}
	
	@PostMapping(path = "/salle")
	public SalleDto add(@RequestBody SalleDto sal) {
		return this.salleService.add(sal);
	}
	
	@PutMapping(path = "/salle/{id}")
	public void update(@RequestBody SalleDto sal,@PathVariable int id ) {
		this.salleService.updateSalle(sal, id);
		
	}
	
	@DeleteMapping(path = "/salle/{id}")
	public void delete(@PathVariable int id) {
		this.salleService.deleteSalle(id);
	}
	
	

}
