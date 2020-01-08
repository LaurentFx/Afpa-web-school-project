package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.entity.Salle;

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
				.map(ts-> this.modelMapper.map(ts,SalleDto.class))
				.collect(Collectors.toList());
		
	}

	@Override
	public SalleDto add(SalleDto sal) {
		
		Salle salle = this.salleRepository.save(this.modelMapper.map(sal,Salle.class));
		sal.setId(salle.getId());
		System.err.println("salle ajoutee");
		return sal;
	}

	@Override
	public boolean updateSalle(SalleDto sal, int id) {
		Optional <Salle> salleOp= this.salleRepository.findById(id);
		if(salleOp.isPresent()) {
			Salle salle = salleOp.get();
			salle.setFraisjournalier(sal.getFraisjournalier());
			salle.setPlacesReservees(sal.getPlacesReservees());
			salle.setPlacesReserveesVIP(sal.getPlacesReserveesVIP());
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

}
