package com.afpa.cda.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.InvitationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.ReservationRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Invitation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Reservation;
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
	private InvitationRepository invitationRepository;

	@Autowired
	private ReservationRepository reservationRepository;

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
						animationDto.setPrix(manif.getAnimation().getPrix());
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
						salleDto.setFraisJournalier(manif.getSalle().getFraisjournalier());
						salleDto.setPlacesVip(manif.getSalle().getPlacesVip());
					}
					manifestationDto.setSalle(salleDto);

					manifestationDto.setPrixBillet(manif.getPrixBillet());
					manifestationDto.setReservations(manif.getReservations());
					manifestationDto.setReservationsVip(manif.getReservationsVip());

					return manifestationDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public boolean findAvailability (ManifestationDto newManifestationDto) {
		List<Manifestation> listManifestations =this.manifestationRepository.findAll();
		long jourEnMs = 86400000;

		if (listManifestations.size()>1) {
			System.out.println("listManifestations "+listManifestations);
			for (Manifestation oldManifestation : listManifestations) {
				if (newManifestationDto.getId()!=oldManifestation.getId()) {
					if (newManifestationDto.getSalle().getId()!=0 && newManifestationDto.getSalle().getId()==oldManifestation.getSalle().getId()) {
						if ((((newManifestationDto.getDateDebut().getTime()/jourEnMs)>=oldManifestation.getDateDebut().getTime()/jourEnMs &&
								(newManifestationDto.getDateDebut().getTime()/jourEnMs)<=oldManifestation.getDateFin().getTime()/jourEnMs)
								|| ((newManifestationDto.getDateFin().getTime()/jourEnMs)>=oldManifestation.getDateDebut().getTime()/jourEnMs &&
								(newManifestationDto.getDateFin().getTime()/jourEnMs)<=oldManifestation.getDateFin().getTime()/jourEnMs)) ||

								((oldManifestation.getDateDebut().getTime()/jourEnMs)>=newManifestationDto.getDateDebut().getTime()/jourEnMs &&
								(oldManifestation.getDateDebut().getTime()/jourEnMs)<=newManifestationDto.getDateFin().getTime()/jourEnMs)
								|| ((oldManifestation.getDateFin().getTime()/jourEnMs)>=newManifestationDto.getDateDebut().getTime()/jourEnMs &&
								(oldManifestation.getDateFin().getTime()/jourEnMs)<=newManifestationDto.getDateFin().getTime()/jourEnMs))
						{
							return false;
						}
					}
				} 
			}
		}
		return true;
	}


	@Override
	public ManifestationDto findById(int id) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
				animationDto.setPrix(manif.getAnimation().getPrix());
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
				salleDto.setFraisJournalier(manif.getSalle().getFraisjournalier());
				salleDto.setPlacesVip(manif.getSalle().getPlacesVip());
			}
			manifestationDto.setSalle(salleDto);

			manifestationDto.setPrixBillet(manif.getPrixBillet());
			
			manifestationDto.setReservations(manif.getReservations());
			manifestationDto.setReservationsVip(manif.getReservationsVip());

		}
		return manifestationDto;
	}

	@Override
	public int add(ManifestationDto manifestationDto) {
		int id=0;
		Optional <Manifestation> manifestationOp = this.manifestationRepository.findManifestationByLabel(manifestationDto.getLabel());
		if (!manifestationOp.isPresent()) {
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
			id=this.manifestationRepository.save(manifestation).getId();
			System.err.println("Manifestation ajoutée ");
			return id;
		}
		return id;
	}

	@Override
	public boolean update(ManifestationDto manifestationDto,int id) {
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
			System.err.println("Manifestation modifiée ");
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
		manifDto.setReservations(animDto.getNbreSpectateursPrevus()-salleDto.getPlacesVip());
		manifDto.setReservationsVip(salleDto.getPlacesVip());

		manifDto.setCout( (animDto.getPrix()+(duree* salleDto.getFraisJournalier())));
		manifDto.setPrixBillet(manifDto.getCout()/(animDto.getNbreSpectateursPrevus()*0.8));

		return manifDto;
	}

	@Override
	public boolean delete(int id) {
		List <Invitation> listInvitations = this.invitationRepository.findInvitationByManifestation(id);
		List <Reservation> listReservations = this.reservationRepository.findReservationByManifestation(id);

		if (listInvitations.isEmpty() && listReservations.isEmpty() && this.manifestationRepository.existsById(id)) {
			this.manifestationRepository.deleteById(id);
			System.err.println("Manifestation supprimée ");
			return true;
		}
		return false;
	}
}
