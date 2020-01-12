package com.afpa.cda.service;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Salle;

@Service
public class ManifestationServiceImpl implements IManifestationService {

	@Autowired
	private ManifestationRepository manifestationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ManifestationDto> findAll() {
		return this.manifestationRepository.findAll()
				.stream()
				.map(m-> ManifestationDto.builder()
						.id(m.getId())
						.nom(m.getNom())
						.dateManifestation(m.getDateManifestation())
						.typeManifestation(m.getTypeManifestation())
						.prixBillet(m.getPrixBillet())
						.salle(SalleDto.builder()
								.id(m.getSalle().getId())
										.label(m.getSalle().getLabel())
										.placesReservees(m.getSalle().getPlacesReservees())
										.placesReserveesVip(m.getSalle().getPlacesReserveesVIP())								
										.build())
								.build())
						.collect(Collectors.toList());	
	}

	
	@Override
	public ManifestationDto findById(int id) {
		Optional <Manifestation> manifOp = this.manifestationRepository.findById(id);
		ManifestationDto manif =null; 
		if(manifOp.isPresent()) {
			Manifestation man= manifOp.get();

			manif=this.modelMapper.map(man,ManifestationDto.class);
		}
		return manif;
	}

	@Override
	public ManifestationDto add(ManifestationDto mani) {
		try {
			Manifestation manif = this.manifestationRepository.save(this.modelMapper.map(mani,Manifestation.class));
			mani.setId(manif.getId());
			mani.setNom(manif.getNom());
			mani.setPrixBillet(manif.getPrixBillet());
			mani.setDateManifestation(manif.getDateManifestation());
			System.err.println("manifestation ajoutée");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}		
		return mani;
	}

	@Override
	public boolean updateManifestation(ManifestationDto manif, int id) {

		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		if(manifOp.isPresent()) {
			Manifestation mani = manifOp.get();
			mani.setNom(manif.getNom());
			mani.setDateManifestation(manif.getDateManifestation());
			mani.setPrixBillet(manif.getPrixBillet());
			mani.setSalle(Salle.builder().id(mani.getId()).build());
			mani.setSalle(Salle.builder().label(mani.getNom()).build());
			this.manifestationRepository.save(mani);			
			System.err.println("manifestation mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteManifestation(int id) {

		if(this.manifestationRepository.existsById(id)) {
			this.manifestationRepository.deleteById(id);
			System.err.println("manifestation supprimée");
			return true;
		}
		return false;
	}

}
