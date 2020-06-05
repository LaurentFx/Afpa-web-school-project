package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@GetMapping(path = "/typesalle/list")
	public List<TypeSalleDto> getAll(){
		return this.typeSalleService.findAll();
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@GetMapping(path = "/typesalle/show/{id}")
	public TypeSalleDto getOne(@PathVariable int id){
		return this.typeSalleService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@PostMapping(path = "/typesalle/add")
	public int add(@RequestBody TypeSalleDto typeSalleDto) {
		return this.typeSalleService.add(typeSalleDto);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@PutMapping(path = "/typesalle/update/{id}")
	public boolean update(@RequestBody TypeSalleDto typ,@PathVariable int id) {
		return this.typeSalleService.update(typ, id);
		
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@DeleteMapping(path = "/typesalle/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return this.typeSalleService.delete(id);
	}
	
	
}
