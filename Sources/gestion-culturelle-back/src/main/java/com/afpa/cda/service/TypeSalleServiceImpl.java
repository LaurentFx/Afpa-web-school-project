package com.afpa.cda.service;

import java.util.List;
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
}
