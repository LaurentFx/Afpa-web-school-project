package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.InvitationDto;
import com.afpa.cda.dto.ManifestationDto;


public interface IInvitationService {

	List<InvitationDto> findAll();

	boolean add(InvitationDto invitationDto);

	InvitationDto findById(int id);

	//	List<UserDto> findByRole(int id);

	List<InvitationDto> findInvitationByUserId(int id);

	List<InvitationDto> findInvitationByManifestationId(int id);

	boolean update(InvitationDto invitationDto, int id);

	boolean updateAdd(ManifestationDto manifestationDto, int id);

	boolean updateSub(ManifestationDto manifestationDto, int id);

	boolean delete(int id);

	boolean deleteAll(int id);
}
