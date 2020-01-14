
package com.afpa.cda.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.entity.Salle;
import com.afpa.cda.entity.TypeSalle;

@Service
public class SalleServiceImpl implements ISalleService {

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private ModelMapper modelMapper;


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
	public SalleDto add(SalleDto sal) {
		try {
		Salle salle = this.salleRepository.save(this.modelMapper.map(sal,Salle.class));
		sal.setId(salle.getId());
		System.err.println("Salle ajoutee");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		return sal;
	}

	
	@Override
	public boolean updateSalle(SalleDto sal, int id) {
		Optional <Salle> salleOp= this.salleRepository.findById(id);
		if(salleOp.isPresent()) {
			// Salle salle = salleOp.get();
			this.salleRepository.save(this.modelMapper.map(sal,Salle.class));
			
//			salle.setLabel(sal.getLabel());
//			salle.setCapacite(sal.getCapacite());
//			salle.setPlacesVip(sal.getPlacesVip());
//			salle.setFraisjournalier(sal.getFraisJournalier());
//			salle.setTypesalle(TypeSalle.builder().label(salle.getLabel()).build());
//			salle.setTypesalle(TypeSalle.builder().id(salle.getId()).build());
//			this.salleRepository.save(salle);
			System.err.println("Salle mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSalle(int id) {
		if(this.salleRepository.existsById(id)) {
			this.salleRepository.deleteById(id);
			System.err.println("Salle supprimee");
			return true;
		}
		return false;
	}

	@Override
	public SalleDto findById(int id) {
		Optional<Salle> salleOp = this.salleRepository.findById(id);
		SalleDto salle=null;
		if(salleOp.isPresent()) {
			Salle sal = salleOp.get();
			salle = this.modelMapper.map(sal,SalleDto.class);
		}
		return salle;
	}
}
