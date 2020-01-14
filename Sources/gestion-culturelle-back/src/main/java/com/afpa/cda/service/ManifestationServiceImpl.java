package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Salle;

@Service
public class ManifestationServiceImpl implements IManifestationService {

	@Autowired
	private ManifestationRepository manifestationRepository;
	
//	@Autowired
//	private SalleRepository salleRepository;
//	
//	@Autowired
//	private AnimationRepository animationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ManifestationDto> findAll() {
		 return this.manifestationRepository.findAll()
				 .stream()
				 .map(m-> this.modelMapper.map(m,ManifestationDto.class))
				 .collect(Collectors.toList());
	}

	@Override
	public ManifestationDto findById(int id) {
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		ManifestationDto manif = null;
		if (manifOp.isPresent()) {
			Manifestation man = manifOp.get();

			manif = this.modelMapper.map(man, ManifestationDto.class);
		}
		return manif;
	}

	@Override
	public ManifestationDto add(ManifestationDto mani) {
		
		Manifestation maniE = this.manifestationRepository.save(this.modelMapper.map(mani,Manifestation.class)); 
		mani.setId(maniE.getId());
		
//		Manifestation maniE = this.modelMapper.map(mani,Manifestation.class);
//		Salle salE = this.salleRepository.findById(mani.getSalle().getId()).get();
//		Animation animE = this.animationRepository.findById(mani.getAnimation().getId()).get();
////		Panier panE = 
//		maniE.setSalle(salE);
//		maniE.setAnimation(animE);
//		Manifestation manifEntity = this.manifestationRepository.save(maniE);
//		mani.setId(manifEntity.getId());
//		mani.setSalle(new SalleDto());
//		mani.getSalle().setId(salE.getId());
//		mani.setAnimation(new AnimationDto());
//		mani.getAnimation().setId(animE.getId());

		return mani;

	}

	@Override
	public boolean updateManifestation(ManifestationDto manif,int id) {
		Optional<Manifestation> manifE = this.manifestationRepository.findById(id);
		if (manifE.isPresent()) {
			Manifestation mn = manifE.get();
			mn.setLabel(manif.getLabel());
			mn.setDate(manif.getDate());
			mn.setCout(manif.getCout());
			mn.setPrixBillet(manif.getPrixBillet());
			mn.setReservations(manif.getReservations());
			mn.setReservationsVip(manif.getReservationsVip());
			mn.setRentabilite(manif.getRentabilite());
			this.manifestationRepository.save(mn);
			return true;
		}
		return false;
	}
	

	@Override
	public boolean deleteManifestation(int id) {

		if (this.manifestationRepository.existsById(id)) {
			this.manifestationRepository.deleteById(id);
			System.err.println("manifestation supprimée");
			return true;
		}
		return false;
	}

}
