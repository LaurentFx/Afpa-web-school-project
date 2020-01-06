package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.TypeSalleDto;


public interface ITypeSalleService {

	List<TypeSalleDto> findAll();

	TypeSalleDto add(TypeSalleDto typ);

	boolean updateTypeSalle(TypeSalleDto typ);

	boolean deleteTypeSalle(TypeSalleDto typ);
	
}
