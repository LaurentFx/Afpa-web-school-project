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
						.placesReservees(s.getPlacesReservees())
						.placesReserveesVIP(s.getPlacesReserveesVIP())
						.typeSalleDto(TypeSalleDto.builder()
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
		System.err.println("salle ajoutee");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		return sal;
	}

//	@Override
//	public boolean updateTypeSalle (TypeSalleDto typ, int id) {
//		Optional <TypeSalle> typeSalleOp = this.typeSalleRepository.findById(id);
//		if(typeSalleOp.isPresent()) {
//			TypeSalle typeSalle = typeSalleOp.get();
//			typeSalle.setLabel(typ.getLabel());
//			this.typeSalleRepository.save(typeSalle);
//			System.err.println("typesalle mise à jour");
//			return true;
//		}
//		return false;
//	}
	
	
	@Override
	public boolean updateSalle(SalleDto sal, int id) {
		Optional <Salle> salleOp= this.salleRepository.findById(id);
		if(salleOp.isPresent()) {
			Salle salle = salleOp.get();
			salle.setLabel(sal.getLabel());
			salle.setPlacesReservees(sal.getPlacesReservees());
			salle.setPlacesReserveesVIP(sal.getPlacesReserveesVIP());
			salle.setFraisjournalier(sal.getFraisjournalier());
			salle.setTypesalle(TypeSalle.builder().label(salle.getLabel()).build());
			salle.setTypesalle(TypeSalle.builder().id(salle.getId()).build());
			this.salleRepository.save(salle);
			System.err.println("salle mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSalle(int id) {
		if(this.salleRepository.existsById(id)) {
			this.salleRepository.deleteById(id);
			System.err.println("salle supprimee");
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
