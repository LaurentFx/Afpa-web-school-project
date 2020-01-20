package com.afpa.cda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Panier;

@Service
public class PanierServiceImpl implements IPanierService {
	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private ManifestationRepository manifestationRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PanierDto> findAll() {

		return this.panierRepository.findAll()
				.stream()
				.map(p -> {
					PanierDto panierDto = new PanierDto();
					panierDto.setId(p.getId());
					panierDto.setDateValidation(p.getDateValidation());
					panierDto.setNumClient(p.getNumClient());
					
					
					
					panierDto.setNbreBillets(p.getNbreBillets());
					panierDto.setTotal(p.getTotal());

			  panierDto.setManifestations(new ArrayList<ManifestationDto>());
			
			for (Manifestation m : p.getManifestations()) {
				panierDto.getManifestations()
						.add(ManifestationDto
								.builder().id(m.getId())
								.label(m.getLabel())
								.animation(AnimationDto.builder()
										.id(m.getAnimation().getId())
										.label(m.getAnimation().getLabel())
										.build())
								.build());
			}
			return panierDto;
		})
				.collect(Collectors.toList());
	}

	@Override
	public PanierDto add(PanierDto panier) {
		Panier panE = this.panierRepository.save(this.modelMapper.map(panier, Panier.class));
		// Panier panE = this.modelMapper.map(panier,Panier.class);
		// Manifestation manifE =
		// this.manifestationRepository.findAll(panier.getManifestations().);
		// Panier entityPan = this.panierRepository.save(panE);
		panier.setId(panE.getId());
		return panier;
	}

	@Override
	public boolean updatePanier(PanierDto panier, int id) {
		Optional<Panier> panUp = this.panierRepository.findById(id);
		if (panUp.isPresent()) {
			this.panierRepository.save(this.modelMapper.map(panier, Panier.class));

			// Panier pr= panUp.get();
			// pr.setNbreBillets(panier.getNbreBillets());
			// pr.setNumClient(panier.getNumClient());
			// this.panierRepository.save(pr);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePanier(int id) {
		if (this.panierRepository.existsById(id)) {
			this.panierRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public PanierDto findById(int id) {
		Optional<Panier> panE = this.panierRepository.findById(id);
		PanierDto panDto = new PanierDto();
		
		if (panE.isPresent()) {
			
			Panier pan= panE.get();
			panDto.setId(pan.getId());
			panDto.setDateValidation(pan.getDateValidation());
			panDto.setNumClient(pan.getNumClient());
			panDto.setNbreBillets(pan.getNbreBillets());
			panDto.setTotal(pan.getTotal());
			
			panDto.setManifestations(new ArrayList<ManifestationDto>());
			
			for (Manifestation m : pan.getManifestations()) {
				panDto.getManifestations()
						.add(ManifestationDto
								.builder().id(m.getId())
								.label(m.getLabel())
								.animation(AnimationDto.builder()
										.id(m.getAnimation().getId())
										.label(m.getAnimation().getLabel())
										.build())
								.build());
			
		}
		
	}
		return panDto;
	}

}