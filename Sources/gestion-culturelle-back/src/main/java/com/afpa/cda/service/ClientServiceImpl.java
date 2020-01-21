package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.ClientDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.entity.User;
@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private UserRepository clientRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ClientDto> findAll() {
		return this.clientRepository.findAll()

				.stream()
				.map(c-> {		
					ClientDto clientDto = new ClientDto();
					clientDto.setId(c.getId());
					clientDto.setNom(c.getNom());
					clientDto.setPrenom(c.getPrenom());			

					RoleDto roleDto = new RoleDto();
					roleDto.setLabel(c.getRole().getLabel());
					clientDto.setRole(roleDto);

					PanierDto panierDto = new PanierDto();
					panierDto.setId(c.getPanier().getId());
					clientDto.setPanier(panierDto);

					return clientDto;
				})

				.collect(Collectors.toList());
	}
	@Override
	public ClientDto add(ClientDto client) {
		User perE = this.clientRepository.save
				(this.modelMapper.map(client, User.class));
		client.setId(perE.getId());
		return client;		

	}
	@Override
	public boolean updateClient(ClientDto client, int id) {

		Optional<User> perE = this.clientRepository.findById(id);
		if (perE.isPresent()) {
			this.clientRepository.save(this.modelMapper.map(client,User.class));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteClient(int id) {
		if (this.clientRepository.existsById(id)) {
			this.clientRepository.deleteById(id);
			System.err.println("client supprimé");
			return true;
		}
		return false;
	}
	@Override
	public ClientDto findById(int id) {

		Optional<User> perE = this.clientRepository.findById(id);
		ClientDto clientDto = null;
		if (perE.isPresent()) {
			User pr = perE.get();
			clientDto = this.modelMapper.map(pr, ClientDto.class);
		}
		return clientDto;
	}
}


