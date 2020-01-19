package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Personne;
import com.afpa.cda.entity.User;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> findAll() {
		return this.UserRepository.findAll()
				.stream()
				.map(me->{ 
					UserDto userDto = this.modelMapper.map(me,UserDto.class);
					// solution temporaire
					// ne pas remonter les mots de passe pour le service get
					userDto.setPassword(null);
					userDto.setTokenSecret(null);
					userDto.setRole(this.modelMapper.map(me.getRole(),RoleDto.class));
					return userDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public UserDto add(UserDto userDto) {
		User userEntity = this.UserRepository.save(this.modelMapper.map(userDto,User.class));
		userDto.setId(userEntity.getId());
		return userDto;
	}

	@Override
	public Optional<UserDto> findById(Integer userId) {
		Optional<User> userEnOpt = this.UserRepository.findById(userId);
		UserDto userDto = new UserDto ();
		if(userEnOpt.isPresent()) {
			User me = userEnOpt.get();
			 userDto = this.modelMapper.map(me,UserDto.class);
			// solution temporaire
			// ne pas remonter les mots de passe pour le service get
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setRole(this.modelMapper.map(me.getRole(),RoleDto.class));
		 return Optional.of(userDto);
		}
		return Optional.empty();
	}

	@Override
		public UserDto findOne(Integer userId) {
		Optional<User> userEnOpt = this.UserRepository.findById(userId);
		UserDto userDto = new UserDto ();
		if(userEnOpt.isPresent()) {
			User me = userEnOpt.get();
			 userDto = this.modelMapper.map(me,UserDto.class);
			// solution temporaire
			// ne pas remonter les mots de passe pour le service get
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setRole(this.modelMapper.map(me.getRole(),RoleDto.class));
		}
		return userDto;
	}
	
	@Override
	public boolean update (UserDto user, int id) {
		Optional<User> userOp = this.UserRepository.findById(id);
		if(userOp.isPresent()) {
			this.UserRepository.save(this.modelMapper.map(user,User.class));			
			System.err.println("user mis à jour");
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delete (int id) {
		
		if(this.UserRepository.existsById(id)) {
			this.UserRepository.deleteById(id);
			System.err.println("user supprimé");
			return true;
		}
		return false;
	
		
	}
	
}

