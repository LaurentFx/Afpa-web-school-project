package com.afpa.cda.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IInvitationService;
@Service
public class InvitationServiceImpl implements IInvitationService {
	
    @Autowired
    private UserRepository vipRepository;
    
    @Autowired
    private ModelMapper modelMapper;

 //   @Override
	public List<UserDto> findAll() {
		return this.vipRepository.findAll()
				.stream()
				.map(v-> { 
					UserDto userDto = new UserDto();
					userDto.setId(v.getId());
					userDto.setNom(v.getNom());
					userDto.setPrenom(v.getPrenom());
				
					
					RoleDto rolDto = new RoleDto();
					rolDto.setId(v.getRole().getId());
					rolDto.setLabel(v.getRole().getLabel());
					userDto.setRole(rolDto);
					
					userDto.setEntreprise(v.getEntreprise());
					return userDto;
				})
				.collect(Collectors.toList());
	}
	
    public List <UserDto> findByRole (int id) {
    	this.vipRepository.findByRoleId(id);
    	
    	return null;
    }
    
    @Override
	public UserDto add(UserDto userDto) {
		User user = this.vipRepository.save
				(this.modelMapper.map(userDto, User.class));
		userDto.setId(user.getId());
		return userDto;
	}
	
	
    @Override
	public UserDto findById(int id) {
		Optional<User> userOp = this.vipRepository.findById(id);
		UserDto userDto = null;
		if (userOp.isPresent()) {
			User user = userOp.get();
			userDto = this.modelMapper.map(user, UserDto.class);
		}
		return userDto;
	}
	
    
    @Override
	public boolean update(UserDto user, int id) {
    	Optional<User> perE = this.vipRepository.findById(id);
    	if (perE.isPresent()) {
			this.vipRepository.save(this.modelMapper.map(user,User.class));
		return true;
    	}
		return false;
	}
	
    
    @Override
	public boolean delete(int id) {
		if (this.vipRepository.existsById(id)) {
			this.vipRepository.deleteById(id);
			System.err.println("user supprimé");
			return true;
		}
		return false;
	}

    
}