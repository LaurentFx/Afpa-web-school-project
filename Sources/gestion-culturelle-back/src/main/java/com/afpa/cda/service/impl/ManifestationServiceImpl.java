package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Salle;
import com.afpa.cda.entity.User;
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
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ManifestationDto> findAll() {

		return this.manifestationRepository.findAll()
				.stream()				.map(manif-> {
					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(manif.getId());
					manifestationDto.setLabel(manif.getLabel());
					manifestationDto.setDateValidation(manif.getDateValidation());

					UserDto userDto = new UserDto ();
					if (manif.getValidateur()!=null) {
						userDto.setId(manif.getValidateur().getId());
						userDto.setNom(manif.getValidateur().getNom());
					}
					manifestationDto.setValidateur(userDto);

					AnimationDto animationDto = new AnimationDto();
					if (manif.getAnimation()!=null) {
						animationDto.setId(manif.getAnimation().getId());
						animationDto.setLabel(manif.getAnimation().getLabel());
						animationDto.setNbreSpectateursPrevus(manif.getAnimation().getNbreSpectateursPrevus());
					}
					manifestationDto.setAnimation(animationDto);

					manifestationDto.setDateDebut(manif.getDateDebut());
					manifestationDto.setDateFin(manif.getDateFin());
					manifestationDto.setCout(manif.getCout());

					SalleDto salleDto = new SalleDto();
					if (manif.getSalle()!=null) {
						salleDto.setId(manif.getSalle().getId());
						salleDto.setLabel(manif.getSalle().getLabel());
					}
					manifestationDto.setSalle(salleDto);

					manifestationDto.setPrixBillet(manif.getPrixBillet());
					manifestationDto.setReservations(manif.getReservations());
					manifestationDto.setReservationsVip(manif.getReservationsVip());

					manifestationDto.setListVips(new ArrayList<UserDto>());
					for (User u : manif.getListVips()) {
						manifestationDto.getListVips()
						.add(UserDto.builder()
								.id(u.getId())
								.nom(u.getNom())
								.build());

					}

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

			UserDto userDto = new UserDto ();
			if (manif.getValidateur()!=null) {
				userDto.setId(manif.getValidateur().getId());
				userDto.setNom(manif.getValidateur().getNom());
			}
			manifestationDto.setValidateur(userDto);

			AnimationDto animationDto = new AnimationDto();
			if (manif.getAnimation()!=null) {
				animationDto.setId(manif.getAnimation().getId());
				animationDto.setLabel(manif.getAnimation().getLabel());
				animationDto.setNbreSpectateursPrevus(manif.getAnimation().getNbreSpectateursPrevus());
			}
			manifestationDto.setAnimation(animationDto);

			manifestationDto.setDateDebut(manif.getDateDebut());
			manifestationDto.setDateFin(manif.getDateFin());
			manifestationDto.setCout(manif.getCout());

			SalleDto salleDto = new SalleDto();
			if (manif.getSalle()!=null) {
				salleDto.setId(manif.getSalle().getId());
				salleDto.setLabel(manif.getSalle().getLabel());
			}
			manifestationDto.setSalle(salleDto);

			manifestationDto.setPrixBillet(manif.getPrixBillet());
			manifestationDto.setReservations(manif.getReservations());
			manifestationDto.setReservationsVip(manif.getReservationsVip());

			manifestationDto.setListVips(new ArrayList<UserDto>());
			for (User u : manif.getListVips()) {
				manifestationDto.getListVips()
				.add(UserDto.builder()
						.id(u.getId())
						.nom(u.getNom())
						.build());
			}

		}
		return manifestationDto;
	}

	@Override
	public ManifestationDto add(ManifestationDto manifestationDto) {
		Manifestation manifestation = new Manifestation ();
		manifestation.setLabel(manifestationDto.getLabel());
		manifestation.setDateValidation(manifestationDto.getDateValidation());

		Optional <User> validOp = this.userRepository.findById(manifestationDto.getValidateur().getId());
		if (validOp.isPresent()) {
			User valid = validOp.get();
			manifestation.setValidateur(valid);
		}

		Optional <Animation> animationOp = this.animationRepository.findById(manifestationDto.getAnimation().getId());
		if (animationOp.isPresent()) {
			Animation animation = animationOp.get();
			manifestation.setAnimation(animation);
		}

		manifestation.setDateDebut(manifestationDto.getDateDebut());		
		manifestation.setDateFin(manifestationDto.getDateFin());

		Optional <Salle> salleOp = this.salleRepository.findById(manifestationDto.getSalle().getId());
		if (salleOp.isPresent()) {
			Salle salle = salleOp.get();
			manifestation.setSalle(salle);
		}

		manifestation.setReservations(manifestationDto.getReservations());
		manifestation.setReservationsVip(manifestationDto.getReservationsVip());
		manifestation.setCout(manifestationDto.getCout());
		manifestation.setPrixBillet(manifestationDto.getPrixBillet());

		this.manifestationRepository.save(manifestation);
		return manifestationDto;

	}

	@Override
	public boolean updateManifestation(ManifestationDto manifestationDto,int id) {

		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);

		if (manifOp.isPresent()) {
			Manifestation manifestation = manifOp.get();

			manifestation.setLabel(manifestationDto.getLabel());
			manifestation.setDateValidation(manifestationDto.getDateValidation());

			Optional <User> validOp = this.userRepository.findById(manifestationDto.getValidateur().getId());
			if (validOp.isPresent()) {
				User valid = validOp.get();
				manifestation.setValidateur(valid);
			}

			Optional <Animation> animationOp = this.animationRepository.findById(manifestationDto.getAnimation().getId());
			if (animationOp.isPresent()) {
				Animation animation = animationOp.get();
				manifestation.setAnimation(animation);
			}

			manifestation.setDateDebut(manifestationDto.getDateDebut());		
			manifestation.setDateFin(manifestationDto.getDateFin());

			Optional <Salle> salleOp = this.salleRepository.findById(manifestationDto.getSalle().getId());
			if (salleOp.isPresent()) {
				Salle salle = salleOp.get();
				manifestation.setSalle(salle);
			}

			manifestationDto=calcul(manifestationDto);
			manifestation.setReservations(manifestationDto.getReservations());
			manifestation.setReservationsVip(manifestationDto.getReservationsVip());
			manifestation.setCout(manifestationDto.getCout());
			manifestation.setPrixBillet(manifestationDto.getPrixBillet());

			this.manifestationRepository.save(manifestation);

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
		manifDto.setReservations(salleDto.getCapacite()-salleDto.getPlacesVip());
		manifDto.setReservationsVip(salleDto.getPlacesVip());

		manifDto.setCout( (animDto.getPrix()+(duree* salleDto.getFraisJournalier())));
		manifDto.setPrixBillet(manifDto.getCout()/(animDto.getNbreSpectateursPrevus()*0.8));

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
}
