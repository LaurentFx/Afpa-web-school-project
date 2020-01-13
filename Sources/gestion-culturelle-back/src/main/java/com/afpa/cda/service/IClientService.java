package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ClientDto;

public interface IClientService {
	
	List<ClientDto> findAll();

	AnimationDto add(ClientDto client);

	boolean update(ClientDto client, int id);

	boolean delete(int id);

	ClientDto findById(int id);

}
