package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.ClientDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Personne;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.Salle;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private PersonneRepository clientRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ClientDto> findAll() {
		return this.clientRepository.findAll()
				.stream()
				.map(c-> ClientDto.builder()
						.id(c.getId())
						.nom(c.getNom())
						.prenom(c.getPrenom())
						.role(RoleDto.builder()
								.id(c.getRoles().getId())
										.label(c.getRoles().getLabel())
										.build()
										.build())
								.collect(Collectors.toList());			}

	@Override
	public ClientDto add(ClientDto client) {

		try {
			Personne person = this.clientRepository.save(this.modelMapper.map(client,Personne.class));
			client.setId(person.getId());
			client.setNom(person.getNom());
			client.setPrenom(person.getPrenom());
			System.err.println("client ajouté");

		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}

		return client;
	}

	@Override
	public boolean updateClient(ClientDto clien, int id) {

		Optional<Personne> clientfOp = this.clientRepository.findById(id);
		if(clientfOp.isPresent()) {
			Personne client = clientfOp.get();
			client.setNom(clien.getNom());
			client.setPrenom(clien.getPrenom());			
			client.setRoles(Role.builder().id(client.getId()).build());
			client.setRole(Role.builder().labelRole(client.getRoles());
			this.clientRepository.save(client);			
			System.err.println("client mise à jour");
			return true;
		}

		return false;
	}

	@Override
	public boolean deleteClient(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClientDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
