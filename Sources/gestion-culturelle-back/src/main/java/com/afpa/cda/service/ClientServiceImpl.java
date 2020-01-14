package com.afpa.cda.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.AnimateurDto;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ClientDto;
@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private PersonneRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public List<ClientDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AnimationDto add(ClientDto client) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(ClientDto client, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ClientDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
		
}