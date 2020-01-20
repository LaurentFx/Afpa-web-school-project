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
				.map(m-> {
					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(m.getId());
					manifestationDto.setLabel(m.getLabel());
					manifestationDto.setDateValidation(m.getDateValidation());

					AdminDto adminDto = new AdminDto ();
					adminDto.setId(m.getValidateur().getId());
					manifestationDto.setValidateur(adminDto);

					AnimationDto animationDto = new AnimationDto();
					animationDto.setLabel(m.getAnimation().getLabel());
					manifestationDto.setAnimation(animationDto);

					manifestationDto.setDateDebut(m.getDateDebut());
					manifestationDto.setDateFin(m.getDateFin());
					manifestationDto.setCout(m.getCout());

					SalleDto salleDto = new SalleDto();
					salleDto.setLabel(m.getSalle().getLabel());
					manifestationDto.setSalle(salleDto);

					manifestationDto.setPrixBillet(m.getPrixBillet());
					manifestationDto.setReservations(m.getReservations());
					manifestationDto.setReservationsVip(m.getReservationsVip());

					adminDto.setNom(m.getAnnulateur().getNom());
					manifestationDto.setAnnulateur(adminDto);

					manifestationDto.setDateAnnulation(m.getDateAnulation());

					return manifestationDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public ManifestationDto findById(int id) {
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);

		ManifestationDto manifDto = new ManifestationDto();

		//		ManifestationDto manif = null;
		if (manifOp.isPresent()) {
			Manifestation manif = manifOp.get();

			manifDto.setId(manif.getId());
			manifDto.setLabel(manif.getLabel());
			manifDto.setDateValidation(manif.getDateValidation());

			AdminDto adminDto = new AdminDto ();
			adminDto.setId(manif.getValidateur().getId());
			manifDto.setValidateur(adminDto);

			AnimationDto animationDto = new AnimationDto();
			animationDto.setLabel(manif.getAnimation().getLabel());
			manifDto.setAnimation(animationDto);

			manifDto.setDateDebut(manif.getDateDebut());
			manifDto.setDateFin(manif.getDateFin());
			manifDto.setCout(manif.getCout());

			SalleDto salleDto = new SalleDto();
			salleDto.setLabel(manif.getSalle().getLabel());
			manifDto.setSalle(salleDto);

			manifDto.setPrixBillet(manif.getPrixBillet());
			manifDto.setReservations(manif.getReservations());
			manifDto.setReservationsVip(manif.getReservationsVip());

			adminDto.setNom(manif.getAnnulateur().getNom());
			manifDto.setAnnulateur(adminDto);

			manifDto.setDateAnnulation(manif.getDateAnulation());


//			manif = this.modelMapper.map(man, ManifestationDto.class);
		}
//		return manif;
		return manifDto;
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
	public boolean updateManifestation(ManifestationDto manifDto,int id) {
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);


		//		ManifestationDto manif = null;
		if (manifOp.isPresent()) {
			Manifestation manif = manifOp.get();
//
//			manifDto.setId(manif.getId());
//			manifDto.setLabel(manif.getLabel());
//			manifDto.setDateValidation(manif.getDateValidation());
//
//			AdminDto adminDto = new AdminDto ();
//			adminDto.setId(manif.getValidateur().getId());
//			manifDto.setValidateur(adminDto);
//
//			AnimationDto animationDto = new AnimationDto();
//			animationDto.setLabel(manif.getAnimation().getLabel());
//			manifDto.setAnimation(animationDto);
//
//			manifDto.setDateDebut(manif.getDateDebut());
//			manifDto.setDateFin(manif.getDateFin());
//			manifDto.setCout(manif.getCout());
//
//			SalleDto salleDto = new SalleDto();
//			salleDto.setLabel(manif.getSalle().getLabel());
//			manifDto.setSalle(salleDto);
//
//			manifDto.setPrixBillet(manif.getPrixBillet());
//			manifDto.setReservations(manif.getReservations());
//			manifDto.setReservationsVip(manif.getReservationsVip());
//
//			adminDto.setNom(manif.getAnnulateur().getNom());
//			manifDto.setAnnulateur(adminDto);
//
//			manifDto.setDateAnnulation(manif.getDateAnulation());


			//return manifDto;
			this.manifestationRepository.save(this.modelMapper.map(manifDto, Manifestation.class));
			return true;
		}
		return false;
//		return manif;
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
