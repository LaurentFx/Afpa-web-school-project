package com.afpa.cda.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dto.AnimateurDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Salle;

@Service
public class ManifestationServiceImpl implements IManifestationService {

	@Autowired
	private ManifestationRepository manifestationRepository;
	
	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ManifestationDto> findAll() {
		 return this.manifestationRepository.findAll()
				 .stream()
				 .map(m-> this.modelMapper.map(m,ManifestationDto.class))
				 .collect(Collectors.toList());
//return this.manifestationRepository.findAll()
//		.stream()
//		.map(m->ManifestationDto.builder()
//				.id(m.getId())
//				.label(m.getLabel())
//				.date(m.getDate())
//				.cout(m.getCout())
//				.prixBillet(m.getPrixBillet())
//				.reservations(m.getReservations())
//				.reservationsVip(m.getReservationsVip())
//				.rentabilite(m.getRentabilite())
//				.salle(SalleDto.builder()
//						.id(m.getSalle().getId())
//						.label(m.getSalle().getLabel())
//						.capacite(m.getSalle().getCapacite())
//						.placesVip(m.getSalle().getPlacesVip())
//						.fraisJournalier(m.getSalle().getFraisjournalier()))
//				.animation(AnimateurDto.builder()
//						.id(m.getAnimation().getId())
//						.label(m.getAnimation().getLabel())
//						.type(m.getAnimation().getType())
//						.prix(m.getAnimation().getPrix())
//						.nbreSpectateursPrevus(m.getAnimation().getNbreSpectateursPrevus()))
//				.paniers(PanierDto.builder()
//						.id(m.getPaniers().getId())
//						.numClient(m.getPaniers().get)))
//				return this.manifestationRepository.findAll()
//						.stream()
//						.map(m-> ManifestationDto.builder()
//								.id(m.getId())
//								.label(m.getLabel())
//								// animation
//								.dateManifestation(m.getDateManifestation())
//								.charges(m.getCharges())
//								.typeManifestation(m.getTypeManifestation())
//								.prixBillet(m.getPrixBillet())
//								.reservations(m.getReservations())
//								.reservationsVip(m.getReservationsVip())
//								.rentabilite(m.getRentabilite())
//								.salle(SalleDto.builder()
//										.id(m.getSalle().getId())
//										.label(m.getSalle().getLabel())
//										.capacite(m.getSalle().getCapacite())
//										.placesVip(m.getSalle().getPlacesVip())								
//										.build())
//								.build())
//						.collect(Collectors.toList());
				
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
		
		Manifestation maniE = this.modelMapper.map(mani,Manifestation.class);
		Salle salE = this.
		
		
		return null;
		// try {
		// Manifestation manif =
		// this.manifestationRepository.save(this.modelMapper.map(mani,Manifestation.class));
		// mani.setId(manif.getId());
		// mani.setLabel(manif.getLabel());
		//
		// // mani.setAnimation(manif.getAnimation());
		//
		// mani.setDateManifestation(manif.getDateManifestation());
		// mani.setCharges(manif.getCharges());
		// mani.setTypeManifestation(manif.getTypeManifestation());
		// mani.setPrixBillet(manif.getPrixBillet());
		// mani.setReservations(manif.getReservations());
		// mani.setReservationsVip(manif.getReservationsVip());
		// mani.setRentabilite(manif.getRentabilite());
		//
		// // A vérifier
		// mani.setSalle(SalleDto.builder().id(manif.getId()).build());
		// mani.setSalle(SalleDto.builder().label(manif.getLabel()).build());
		//
		// System.err.println("manifestation ajoutée");
		// } catch (Exception e) {
		// System.err.println(e.getStackTrace());
		// }
		// return mani;
	}

	@Override
	public boolean updateManifestation(ManifestationDto manif, int id) {
		return false;

		// Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		// if(manifOp.isPresent()) {
		// Manifestation mani = manifOp.get();
		// mani.setLabel(manif.getLabel());
		// // animation
		// mani.setDateManifestation(manif.getDateManifestation());
		// mani.setCharges(manif.getCharges());
		// mani.setTypeManifestation(manif.getTypeManifestation());
		// mani.setPrixBillet(manif.getPrixBillet());
		// mani.setReservations(manif.getReservations());
		// mani.setReservationsVip(manif.getReservationsVip());
		// mani.setRentabilite(manif.getReservationsVip());
		//
		//// mani.setSalle(Salle.builder().id(mani.getId()).build());
		//// mani.setSalle(Salle.builder().label(mani.getLabel()).build());
		//
		// this.manifestationRepository.save(mani);
		// System.err.println("manifestation mise à jour");
		// return true;
		// }
		// return false;
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
