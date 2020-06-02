package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.dto.UserDto;

public interface IUserService {

	List<UserDto> findAll();

	int add(UserDto user);

	Optional<UserDto> findByCurrentUser(Integer userId);

	UserDto findById(Integer userId);

	boolean update(UserDto user, int id);

	boolean delete(int id);

	boolean addClient(UserDto userDto);

	List<UserDto> findByRole(int id);
	
	List<UserDto> findVipsToInvite(int id);

}
