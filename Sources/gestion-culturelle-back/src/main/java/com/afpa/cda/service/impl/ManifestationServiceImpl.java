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
import com.afpa.cda.dto.AdminDto;
import com.afpa.cda.dto.AnimateurDto;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.CommandeDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Commande;
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
				.stream()
				.map(manif-> {
					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(manif.getId());
					manifestationDto.setLabel(manif.getLabel());
					manifestationDto.setDateValidation(manif.getDateValidation());

					UserDto userDto = new UserDto ();
					userDto.setId(manif.getValidateur().getId());
					userDto.setNom(manif.getValidateur().getNom());
					manifestationDto.setValidateur(userDto);

					//					userDto = new UserDto ();
					//					userDto.setId(manif.getAnnulateur().getId());
					//					userDto.setNom(manif.getAnnulateur().getNom());
					//					manifestationDto.setAnnulateur(adminDto);

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

					//					manifestationDto.setDateAnnulation(manif.getDateAnnulation());

					manifestationDto.setListCommandes(new ArrayList<CommandeDto>());

					for (Commande m : manif.getListCommandes()) {
						manifestationDto.getListCommandes()
						.add(CommandeDto
								.builder()
								.id(m.getId())
								.panier(PanierDto.builder()
										.id(m.getPanier().getId())
										.dateValidation(m.getPanier().getDateValidation())
										.build())
								.manifestation(ManifestationDto.builder()
										.id(m.getManifestation().getId())
										.label(m.getManifestation().getLabel())
										.prixBillet(m.getManifestation().getPrixBillet())
										.build())
								.build());
					}

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
			userDto.setId(manif.getValidateur().getId());
			userDto.setNom(manif.getValidateur().getNom());
			manifestationDto.setValidateur(userDto);

			//			adminDto = new AdminDto ();
			//			adminDto.setId(manif.getAnnulateur().getId());
			//			adminDto.setNom(manif.getAnnulateur().getNom());
			//			manifestationDto.setAnnulateur(adminDto);

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

			//			manifestationDto.setDateAnnulation(manif.getDateAnnulation());

			manifestationDto.setListCommandes(new ArrayList<CommandeDto>());
			for (Commande m : manif.getListCommandes()) {
				manifestationDto.getListCommandes()
				.add(CommandeDto
						.builder()
						.id(m.getId())
						.panier(PanierDto.builder()
								.id(m.getPanier().getId())
								.dateValidation(m.getPanier().getDateValidation())
								.build())
						.manifestation(ManifestationDto.builder()
								.id(m.getManifestation().getId())
								.label(m.getManifestation().getLabel())
								.prixBillet(m.getManifestation().getPrixBillet())
								.build())
						.build());
			}

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

//		Manifestation manifestation = new Manifestation ();
//		manifestation.setLabel(manifestationDto.getLabel());
//		manifestation.setDateValidation(manifestationDto.getDateValidation());
//
//		Optional <User> validOp = this.userRepository.findById(manifestationDto.getValidateur().getId());
//		if (validOp.isPresent()) {
//			User valid = validOp.get();
//			UserDto validDto = modelMapper.map(valid,UserDto.class);
//
//			manifestationDto.setValidateur(validDto);
//
//			Optional <Animation> animationOp = this.animationRepository.findById(manifestationDto.getAnimation().getId());
//			if (animationOp.isPresent()) {
//				Animation animation = animationOp.get();
//				AnimationDto animationDto = modelMapper.map(animation,AnimationDto.class);
//
//				manifestationDto.setAnimation(animationDto);
//				manifestation.setDateDebut(manifestationDto.getDateDebut());		
//				manifestation.setDateFin(manifestationDto.getDateFin());
//
//				Optional <Salle> salleOp = this.salleRepository.findById(manifestationDto.getSalle().getId());
//				System.out.println("SALLE id "+manifestationDto.getSalle().getId());
//				if (salleOp.isPresent()) {
//					Salle salle = salleOp.get();
//					SalleDto salleDto = modelMapper.map(salle,SalleDto.class);
//					System.out.println("SALLE Dto label : "+salleDto.getLabel());
//
//					manifestationDto.setSalle(salleDto);
//
//					manifestationDto=calcul(manifestationDto);
//
//					manifestation.setRentabilite(0);
//
//					manifestation.setDateAnnulation(manifestationDto.getDateAnnulation());
//
//					Optional <User> annulOp = this.userRepository.findById(manifestationDto.getAnnulateur().getId());
//					if (annulOp.isPresent()) {
//						User annul = annulOp.get();
//						UserDto annulDto = modelMapper.map(annul,UserDto.class);
//						manifestationDto.setValidateur(annulDto);
//					}
//				}
//			}
//		}
//		System.out.println(manifestation.toString());
//		this.manifestationRepository.save(manifestation);


				
//				manifestationDto.setAnnulateur("null");
//				manifestationDto.setDateAnnulation(null);
			
//				Manifestation manifestation = this.modelMapper.map(manifestationDto,Manifestation.class);
				
				
				
				Manifestation maniE = this.manifestationRepository.save(this.modelMapper.map(manifestationDto,Manifestation.class)); 
				manifestationDto.setId(maniE.getId());
		
				manifestationDto=calcul(manifestationDto);
				
				this.manifestationRepository.save(this.modelMapper.map(manifestationDto,Manifestation.class)); 


		return manifestationDto;

	}


	@Override
	public boolean updateManifestation(ManifestationDto manifDto,int id) {

		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);

		if (manifOp.isPresent()) {
			Manifestation maniE = manifOp.get();

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
		manifDto.setReservations(animDto.getNbreSpectateursPrevus());
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

	public void annulation (AdminDto annulateur) {

	}
}
