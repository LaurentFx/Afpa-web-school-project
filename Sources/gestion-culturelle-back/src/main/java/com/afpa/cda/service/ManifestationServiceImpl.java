package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dto.AdminDto;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Manifestation;

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
	public ManifestationDto add(ManifestationDto mani) {

		Manifestation maniE = this.manifestationRepository.save(this.modelMapper.map(mani,Manifestation.class)); 
		mani.setId(maniE.getId());

		return mani;

	}

	@Override
	public boolean updateManifestation(ManifestationDto manifDto,int id) {
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);

		if (manifOp.isPresent()) {
			Manifestation manif = manifOp.get();

			this.manifestationRepository.save(this.modelMapper.map(manifDto, Manifestation.class));
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


	public double calculCout (double prixAnim,double fraisJournalier, int nbreJours) {
		int cout = 0;

		return cout;
	}

	public double calculPrixBillet (double cout, int capacite) {
		int prixBillet = 0;

		return prixBillet;
	}

	public void annulation (AdminDto annulateur) {


	}
}
