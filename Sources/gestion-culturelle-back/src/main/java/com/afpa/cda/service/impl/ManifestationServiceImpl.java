package com.afpa.cda.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dto.AdminDto;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Salle;
import com.afpa.cda.service.IManifestationService;

@Service
public class ManifestationServiceImpl implements IManifestationService {

	@Autowired
	private ManifestationRepository manifestationRepository;

	@Autowired
	private AnimationRepository animationRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ManifestationDto> findAll() {

		return this.manifestationRepository.findAll()
				.stream()
				.map(manif-> {
					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(manif.getId());
					manifestationDto.setLabel(manif.getLabel());
					manifestationDto.setDateValidation(manif.getDateValidation());

					AdminDto adminDto = new AdminDto ();
					adminDto.setId(manif.getValidateur().getId());
					adminDto.setNom(manif.getValidateur().getNom());
					manifestationDto.setValidateur(adminDto);

					adminDto = new AdminDto ();
					adminDto.setId(manif.getAnnulateur().getId());
					adminDto.setNom(manif.getAnnulateur().getNom());
					manifestationDto.setAnnulateur(adminDto);

					AnimationDto animationDto = new AnimationDto();
					animationDto.setLabel(manif.getAnimation().getLabel());
					manifestationDto.setAnimation(animationDto);

					manifestationDto.setDateDebut(manif.getDateDebut());
					manifestationDto.setDateFin(manif.getDateFin());
					manifestationDto.setCout(manif.getCout());

					SalleDto salleDto = new SalleDto();
					salleDto.setLabel(manif.getSalle().getLabel());
					manifestationDto.setSalle(salleDto);

					manifestationDto.setPrixBillet(manif.getPrixBillet());
					manifestationDto.setReservations(manif.getReservations());
					manifestationDto.setReservationsVip(manif.getReservationsVip());



					manifestationDto.setDateAnnulation(manif.getDateAnnulation());


					return manifestationDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public ManifestationDto findById(int id) {
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);

		ManifestationDto manifestationDto = new ManifestationDto();

		if (manifOp.isPresent()) {
			Manifestation manif = manifOp.get();

			manifestationDto.setId(manif.getId());
			manifestationDto.setLabel(manif.getLabel());
			manifestationDto.setDateValidation(manif.getDateValidation());

			AdminDto adminDto = new AdminDto ();
			adminDto.setId(manif.getValidateur().getId());
			adminDto.setNom(manif.getValidateur().getNom());
			manifestationDto.setValidateur(adminDto);

			adminDto = new AdminDto ();
			adminDto.setId(manif.getAnnulateur().getId());
			adminDto.setNom(manif.getAnnulateur().getNom());
			manifestationDto.setAnnulateur(adminDto);

			AnimationDto animationDto = new AnimationDto();
			animationDto.setLabel(manif.getAnimation().getLabel());
			manifestationDto.setAnimation(animationDto);

			manifestationDto.setDateDebut(manif.getDateDebut());
			manifestationDto.setDateFin(manif.getDateFin());
			manifestationDto.setCout(manif.getCout());

			SalleDto salleDto = new SalleDto();
			salleDto.setLabel(manif.getSalle().getLabel());
			manifestationDto.setSalle(salleDto);

			manifestationDto.setPrixBillet(manif.getPrixBillet());
			manifestationDto.setReservations(manif.getReservations());
			manifestationDto.setReservationsVip(manif.getReservationsVip());

			manifestationDto.setDateAnnulation(manif.getDateAnnulation());

		}
		return manifestationDto;
	}

	@Override
	public ManifestationDto add(ManifestationDto manifDto) {

		Manifestation maniE = this.manifestationRepository.save(this.modelMapper.map(manifDto,Manifestation.class)); 
		manifDto.setId(maniE.getId());

		manifDto=calcul(manifDto);

		this.manifestationRepository.save(this.modelMapper.map(manifDto,Manifestation.class)); 
		return manifDto;

	}


	@Override
	public boolean updateManifestation(ManifestationDto manifDto,int id) {

		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);

		if (manifOp.isPresent()) {
			Manifestation maniE = manifOp.get();
			//			this.manifestationRepository.save(this.modelMapper.map(manifDto, Manifestation.class));
			//		Manifestation maniE = this.manifestationRepository.save(this.modelMapper.map(manifDto,Manifestation.class)); 

			manifDto.setId(maniE.getId());

			manifDto=calcul(manifDto);

			this.manifestationRepository.save(this.modelMapper.map(manifDto,Manifestation.class)); 

			return true;
		}
		return false;
	}

	@Override
	public ManifestationDto calcul (ManifestationDto manifDto) {
		
		double debut=manifDto.getDateDebut().getTime();
		double fin=manifDto.getDateFin().getTime();
		double duree = 1+((((fin-debut)/1000)/3600)/24);
		AnimationDto animDto = new AnimationDto();
		Optional<Animation> animOp=animationRepository.findById(manifDto.getAnimation().getId());
		if (animOp.isPresent()) {
			Animation anim = animOp.get();
			animDto=modelMapper.map(anim,AnimationDto.class);
		}

		SalleDto salleDto = new SalleDto ();
		Optional<Salle> salleOp=salleRepository.findById(manifDto.getSalle().getId());
		if (salleOp.isPresent()) {
			Salle salle = salleOp.get();
			salleDto = modelMapper.map(salle,SalleDto.class);
		}

		manifDto.setCout((int) (animDto.getPrix()+(duree* salleDto.getFraisJournalier())));
		manifDto.setPrixBillet((int) ((manifDto.getCout()/salleDto.getCapacite())*0.8));
		
		return manifDto;
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



	public void annulation (AdminDto annulateur) {


	}
}
