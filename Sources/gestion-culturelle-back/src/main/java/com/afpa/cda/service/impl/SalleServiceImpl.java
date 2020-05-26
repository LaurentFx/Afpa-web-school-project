
package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Salle;
import com.afpa.cda.entity.TypeSalle;
import com.afpa.cda.service.IManifestationService;
import com.afpa.cda.service.ISalleService;

@Service
public class SalleServiceImpl implements ISalleService {

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private TypeSalleRepository typeSalleRepository;

	@Autowired
	private ManifestationRepository manifestationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IManifestationService manifestationService;


	@Override
	public List<SalleDto> findAll() {
		return this.salleRepository.findAll()
				.stream()
				.map(s-> SalleDto.builder()
						.id(s.getId())
						.label(s.getLabel())
						.capacite(s.getCapacite())
						.placesVip(s.getPlacesVip())
						.fraisJournalier(s.getFraisjournalier())
						.typeSalle(TypeSalleDto.builder()
								.id(s.getTypesalle().getId())
								.label(s.getTypesalle().getLabel())
								.build())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public SalleDto findById(int id) {
		Optional<Salle> salleOp = this.salleRepository.findById(id);
		SalleDto salleDto = new SalleDto();
		if(salleOp.isPresent()) {
			Salle salle = salleOp.get();

			salleDto.setId(salle.getId());
			salleDto.setLabel(salle.getLabel());
			salleDto.setCapacite(salle.getCapacite());
			salleDto.setPlacesVip(salle.getPlacesVip());
			salleDto.setFraisJournalier(salle.getFraisjournalier());

			TypeSalleDto typeSalleDto = new TypeSalleDto();
			typeSalleDto.setId(salle.getTypesalle().getId());
			typeSalleDto.setLabel(salle.getTypesalle().getLabel());
			salleDto.setTypeSalle(typeSalleDto);

		}
		return salleDto;
	}

	@Override
	public List<SalleDto> findAllByCapacity(int id) {
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		List <SalleDto> listSallesDto = new ArrayList<SalleDto>();

		if (manifOp.isPresent()) {
			Manifestation manifestation = manifOp.get();
			int nbreSpectateursPrevus=manifestation.getAnimation().getNbreSpectateursPrevus();
			List <Salle> listSalles = this.salleRepository.findAll();
			for (Salle salle : listSalles) {
				if (salle.getCapacite()>=nbreSpectateursPrevus) {
					SalleDto salleDto = this.modelMapper.map(salle,SalleDto.class);
					listSallesDto.add(salleDto);
				}
			}
		}
		return listSallesDto;
	}



	@Override
	public boolean add(SalleDto salleDto) {

		Optional<Salle> salleOp = this.salleRepository.findByLabel(salleDto.getLabel());
		if (!salleOp.isPresent()) {
			Salle salle = new Salle ();
			salle.setLabel(salleDto.getLabel());
			salle.setCapacite(salleDto.getCapacite());
			salle.setPlacesVip(salleDto.getPlacesVip());
			salle.setFraisjournalier(salleDto.getFraisJournalier());

			Optional <TypeSalle> typesalleOp = this.typeSalleRepository.findById(salleDto.getTypeSalle().getId());
			if (typesalleOp.isPresent()) {
				TypeSalle typesalle = typesalleOp.get();
				TypeSalleDto typesalleDto = modelMapper.map(typesalle,TypeSalleDto.class);			

				salleDto.setTypeSalle(typesalleDto);
			}

			this.salleRepository.save(this.modelMapper.map(salleDto,Salle.class));
			return false;
		}

		return true;
	}


	@Override
	public boolean updateSalle(SalleDto salleDto, int id) {
		Optional <Salle> salleOp= this.salleRepository.findById(id);
		if(salleOp.isPresent()) {
			Salle salle = salleOp.get();
			salle=this.modelMapper.map(salleDto,Salle.class);
			this.salleRepository.save(salle);

			List <Manifestation> listManifestations = manifestationRepository.findManifestationBySalleId(id);
			if (!listManifestations.isEmpty()) {
				for (Manifestation manifestation : listManifestations) {
					ManifestationDto manifestationDto = modelMapper.map(manifestation,ManifestationDto.class);
					manifestationDto=manifestationService.calcul(manifestationDto);
					Manifestation manif = modelMapper.map(manifestationDto,Manifestation.class);

					Optional<Manifestation> manifOp = this.manifestationRepository.findById(manifestation.getId());
					if (manifOp.isPresent()) {
						this.manifestationRepository.save(manif);
					}

				}
			}

			return true;
		}
		return false;
	}


	@Override
	public boolean deleteSalle(int id) {
		List <Manifestation> listManifestations = manifestationRepository.findManifestationBySalleId(id);

		if (listManifestations.isEmpty() && this.salleRepository.existsById(id)) {

			this.salleRepository.deleteById(id);
			return true;
		}
		return false;
	}


}
