package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afpa.cda.dao.RoleRepository;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<RoleDto> findAll() {
		return this.roleRepository.findAll()
				.stream()
				.map(ts-> this.modelMapper.map(ts,RoleDto.class))
				.collect(Collectors.toList());	
	}

	@Override
	public RoleDto add(RoleDto rol) {
		Role role = this.roleRepository.save(this.modelMapper.map(rol,Role.class));
		rol.setId(role.getId());
		return rol;
	}

	@Override
	public boolean updateRole(RoleDto rol) {
		Optional <Role> roleOp = this.roleRepository.findById(rol.getId());
		if(roleOp.isPresent()) {
			Role role = roleOp.get();
			role.setLabelRole(rol.getLabelRole());
			this.roleRepository.save(role);
			return true;
		}

		return false;
	}

	@Override
	public boolean deleteRole(RoleDto rol) {

		if(this.roleRepository.existsById(rol.getId())) {
			this.roleRepository.deleteById(rol.getId());
			return true;
		}

		return false;
	}
}
