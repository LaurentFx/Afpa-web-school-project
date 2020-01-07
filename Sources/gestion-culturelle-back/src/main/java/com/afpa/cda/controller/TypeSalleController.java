package com.afpa.cda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.service.ITypeSalleService;

@RestController
public class TypeSalleController {

	@Autowired
	private ITypeSalleService typeSalleService;
	
	@GetMapping(path = "/typesalle")
	public List<TypeSalleDto> getAll(){
		return this.typeSalleService.findAll();
	}
	
	@PostMapping(path = "/typesalle")
	public TypeSalleDto add(@RequestBody TypeSalleDto typ) {
		return this.typeSalleService.add(typ);
	}
	
	@PutMapping(path = "/typesalle")
	public void update(@RequestBody TypeSalleDto typ) {
		this.typeSalleService.updateTypeSalle(typ);
	}
	
	@DeleteMapping(path = "/typesalle")
	public void delete(@RequestBody TypeSalleDto typ) {
		this.typeSalleService.deleteTypeSalle(typ);
	}	
	
}
