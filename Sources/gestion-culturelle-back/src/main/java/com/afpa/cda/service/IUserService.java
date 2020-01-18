package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.dto.UserDto;

public interface IUserService {

	List<UserDto> findAll();

	UserDto add(UserDto user);

	Optional<UserDto> findById(Integer userId);

}
