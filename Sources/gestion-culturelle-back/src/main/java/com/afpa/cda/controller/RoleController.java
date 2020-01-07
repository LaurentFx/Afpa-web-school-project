package com.afpa.cda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(path = "/role")
	public List<RoleDto> getAll(){
		return this.roleService.findAll();
	}

	@PostMapping(path = "/role")
	public RoleDto add(@RequestBody RoleDto rol) {
		return this.roleService.add(rol);
	}

	@PutMapping(path = "/role")
	public void update(@RequestBody RoleDto rol) {
		this.roleService.updateRole(rol);
	}

	@DeleteMapping(path = "/role")
	public void delete(@RequestBody RoleDto rol) {
		this.roleService.deleteRole(rol);
	}

}



