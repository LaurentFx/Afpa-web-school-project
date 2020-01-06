package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.service.ITypeSalleService;

@RestController
public class TypeSalleController {

	@Autowired
	private ITypeSalleService typeSalleService;
	
	@GetMapping(path = "/messages")
	public List<TypeSalleDto> getAll(){
		return this.typeSalleService.findAll();
	}
	
	@PostMapping(path = "/messages")
	public TypeSalleDto add(@RequestBody TypeSalleDto msg) {
		return this.typeSalleService.add(msg);
	}
	
}
