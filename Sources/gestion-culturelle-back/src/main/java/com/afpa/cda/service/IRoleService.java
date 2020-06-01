package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.RoleDto;


public interface IRoleService {
	
	List<RoleDto> findAll();

	boolean add(RoleDto rol);

	boolean update(RoleDto rol, int id);

	boolean delete(int id);

	RoleDto findById(int id);

}
