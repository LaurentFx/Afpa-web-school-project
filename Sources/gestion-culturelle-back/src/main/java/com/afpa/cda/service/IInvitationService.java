package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.UserDto;


public interface IInvitationService {

	List<UserDto> findAll();

	UserDto add(UserDto userDto);

	UserDto findById(int id);

	boolean delete(int id);

	List<UserDto> findByRole(int id);

	List<UserDto> findAllVipsByManifestation(int id);

	boolean updateAdd(ManifestationDto manifestationDto, int id);

	boolean updateSub(ManifestationDto manifestationDto, int id);
	
}
