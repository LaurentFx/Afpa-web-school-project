package com.afpa.cda.service;

import java.util.List;
import com.afpa.cda.dto.RoleDto;

public interface IRoleService {
	
	List<RoleDto> findAll();

	RoleDto add(RoleDto rol);

	boolean updateRole(RoleDto typ);

	boolean deleteRole(RoleDto typ);

}
