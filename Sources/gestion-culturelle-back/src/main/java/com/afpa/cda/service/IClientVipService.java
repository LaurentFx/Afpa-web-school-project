package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ClientDto;
import com.afpa.cda.dto.ClientVipDto;

public interface IClientVipService {
	
	List<ClientVipDto> findAll();

	ClientVipDto add(ClientVipDto clientvip);

	boolean updateClient(ClientVipDto clientvip, int id);

	boolean deleteClientVip(int id);

	ClientDto findById(int id);

}
