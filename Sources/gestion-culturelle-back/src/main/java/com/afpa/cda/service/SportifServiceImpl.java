package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.SportifDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Personne;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.Salle;

@Service
public class SportifServiceImpl implements ISportifService {

	@Autowired
	private PersonneRepository sportifRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SportifDto> findAll() {
		return this.sportifRepository.findAll()
				.stream()
				.map(c-> SportifDto.builder()
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
	public SportifDto add(SportifDto sportif) {

		try {
			Personne sport = this.sportifRepository.save(this.modelMapper.map(client,Personne.class));
			sportif.setId(sport.getId());		
			System.err.println("sportif ajouté");

		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}

		return sportif;
	}

	@Override
	public boolean updateSportif(SportifDto sport, int id) {

		Optional<Personne> sportiffOp = this.sportifRepository.findById(id);
		if(sportiffOp.isPresent()) {
			Personne sportif = sportiffOp.get();
			sportif.setNom(sport.getNom());
			sportif.setPrenom(sport.getPrenom());			
			sportif.setRole(Role.builder().id(sportif.getId()).build());
			sportif.setRole(Role.builder().Role(sportif.getRole());
			this.sportifRepository.save(sportif);			
			System.err.println("sportif mise à jour");
			return true;
		}

		return false;
	}

	@Override
	public boolean deleteSportif(int id) {
		if(this.sportifRepository.existsById(id)) {
			this.sportifRepository.deleteById(id);
			System.err.println("sportif supprimé");
			return true;
		}

		return false;
	}

	@Override
	public SportifDto findById(int id) {
		Optional<Personne> sportifOp = this.sportifRepository.findById(id);
		SportifDto sportif=null;
		if(sportifOp.isPresent()) {
			Personne sport = sportifOp.get();
			sportif = this.modelMapper.map(sport,SportifDto.class);
		}
		return sportif;
	}

}
