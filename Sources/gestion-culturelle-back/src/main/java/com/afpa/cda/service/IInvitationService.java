package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.UserDto;


public interface IInvitationService {

	List<UserDto> findAll();

	UserDto add(UserDto userDto);

	UserDto findById(int id);

	boolean update(UserDto user, int id);

	boolean delete(int id);

	List<UserDto> findByRole(int id);
	
}
