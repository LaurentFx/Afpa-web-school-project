package com.afpa.cda.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.entity.Salle;
import com.afpa.cda.entity.TypeSalle;
import com.afpa.cda.service.ITypeSalleService;

@Service
public class TypeSalleServiceImpl implements ITypeSalleService {
	@Autowired
	private TypeSalleRepository typeSalleRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TypeSalleDto> findAll() {
		return this.typeSalleRepository.findAll()
				.stream()
				.map(ts-> this.modelMapper.map(ts,TypeSalleDto.class))
				.collect(Collectors.toList());
	}
	@Override
	public TypeSalleDto findById(int id) {
		Optional <TypeSalle> typeSalleOp = this.typeSalleRepository.findById(id);
		TypeSalleDto tps =null; 
		if(typeSalleOp.isPresent()) {
			TypeSalle typeSalle = typeSalleOp.get();

			tps=this.modelMapper.map(typeSalle,TypeSalleDto.class);
		}
		return tps;
	}

	@Override
	public int add(TypeSalleDto typeSalleDto) {
		int id=0;
		Optional <TypeSalle> typeSalleOp = this.typeSalleRepository.findTypeSalleByLabel(typeSalleDto.getLabel());
		if (!typeSalleOp.isPresent()) {
			id=this.typeSalleRepository.save(this.modelMapper.map(typeSalleDto,TypeSalle.class)).getId();
			return id;
		}
		return id;
	}

	@Override
	public boolean update (TypeSalleDto typ, int id) {
		Optional <TypeSalle> typeSalleOp = this.typeSalleRepository.findById(id);
		if(typeSalleOp.isPresent()) {
			TypeSalle typeSalle = typeSalleOp.get();
			typeSalle.setLabel(typ.getLabel());
			this.typeSalleRepository.save(typeSalle);
			System.err.println("typesalle mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		List <Salle> listSalles = salleRepository.findSalleByTypeSalle(id);

		if (listSalles.isEmpty() && this.typeSalleRepository.existsById(id)) {
			this.typeSalleRepository.deleteById(id);
			return true;
		}
		return false;
	}   
}