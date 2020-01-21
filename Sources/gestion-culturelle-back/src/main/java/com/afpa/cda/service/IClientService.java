package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ClientDto;

public interface IClientService {
	
	List<ClientDto> findAll();

	ClientDto add(ClientDto client);

	boolean updateClient(ClientDto client, int id);

	boolean deleteClient(int id);

	ClientDto findById(int id);

}
