package com.afpa.cda.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.ArtisteDto;
import com.afpa.cda.entity.Personne;
import com.afpa.cda.entity.Role;


@Service
public class ArtisteServiceImpl implements IArtisteService {

	@Autowired
	private PersonneRepository artisteRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public List<ArtisteDto> findAll() {
		return this.artisteRepository.findAll()
				.stream()
				.map(a-> ArtisteDto.builder()
						.id(a.getId())
						.nom(a.getNom())
						.prenom(a.getPrenom())
						.salaire(a.getSalaire())
						
						.role(RoleDto.builder()
								.id(c.getRoles().getId()
										.label(c.getRoles().getLabel())
										.build()
										.build())
								.collect(Collectors.toList());		
	}	


	@Override
	public ArtisteDto add(ArtisteDto artiste) {
		try {
		Personne artist = this.artisteRepository.save(this.modelMapper.map(artiste,Personne.class));
		artiste.setId(artist.getId());
		artiste.setNom(artist.getNom());
		artiste.setPrenom(artist.getPrenom());
		artiste.setSalaire(artist.getSalaire());	
		
		System.err.println("artiste ajouté");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		return artiste;
	}
	
	
	@Override
	public boolean updateArtiste(ArtisteDto artist, int id) {
		Optional <Personne> artisteOp= this.artisteRepository.findById(id);
		if(artisteOp.isPresent()) {
			Personne artiste = artisteOp.get();
			artiste.setNom(artist.getNom());
			artiste.setPrenom(artist.getPrenom());
			artiste.setSalaire(artist.getSalaire());
			artiste.setRoles(Role.builder().id(artiste.getId().build());
			this.artisteRepository.save(artiste);
			System.err.println("artiste mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteArtiste(int id) {
		if(this.artisteRepository.existsById(id)) {
			this.artisteRepository.deleteById(id);
			System.err.println("artiste supprimee");
			return true;
		}
		return false;
	}

	@Override
	public ArtisteDto findById(int id) {
		Optional<Personne> artisteOp = this.artisteRepository.findById(id);
		ArtisteDto artiste=null;
		if(artisteOp.isPresent()) {
			Personne artist = artisteOp.get();
			artiste = this.modelMapper.map(artist,ArtisteDto.class);
		}
		return artiste;
	}



}
