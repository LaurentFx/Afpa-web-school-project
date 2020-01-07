package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.entity.TypeSalle;


@Service
public class TypeSalleServiceImpl implements ITypeSalleService {


	@Autowired
	private TypeSalleRepository typeSalleRepository;

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
	public TypeSalleDto add(TypeSalleDto typ) {
		TypeSalle typeSalle = this.typeSalleRepository.save(this.modelMapper.map(typ,TypeSalle.class));
		typ.setId(typeSalle.getId());
		return typ;

	}

	@Override
	public boolean updateTypeSalle (TypeSalleDto typ, int id) {
		Optional <TypeSalle> typeSalleOp = this.typeSalleRepository.findById(id);
		if(typeSalleOp.isPresent()) {
			TypeSalle typeSalle = typeSalleOp.get();
			typeSalle.setLabel(typ.getLabel());
			this.typeSalleRepository.save(typeSalle);
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteTypeSalle (int id) {
		if(this.typeSalleRepository.existsById(id)) {
			this.typeSalleRepository.deleteById(id);
			return true;
		}
		return false;
	}	

}
