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

import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.service.IRoleService;

@RestController
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@PreAuthorize("hasAnyAuthority('RESP')")
	@GetMapping(path = "/role/list")
	public List<RoleDto> getAll(){
		return this.roleService.findAll();
	}

	@PreAuthorize("hasAnyAuthority('RESP')")
	@GetMapping(path = "/role/show/{id}")
	public RoleDto getOne(@PathVariable int id){
		return this.roleService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@PostMapping(path = "/role/add")
	public int add(@RequestBody RoleDto rol) {
		return this.roleService.add(rol);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@PutMapping(path = "/role/update/{id}")
	public boolean update(@RequestBody RoleDto rol, @PathVariable int id) {
		return this.roleService.update(rol,id);
	}

	@PreAuthorize("hasAnyAuthority('RESP')")
	@DeleteMapping(path = "/role/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return this.roleService.delete(id);
	}

}



