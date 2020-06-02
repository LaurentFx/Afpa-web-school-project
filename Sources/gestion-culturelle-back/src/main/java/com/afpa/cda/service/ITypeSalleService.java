package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.TypeSalleDto;


public interface ITypeSalleService {

	List<TypeSalleDto> findAll();

	int add(TypeSalleDto typ);

	boolean update(TypeSalleDto typ, int id);

	boolean delete(int id);

	TypeSalleDto findById(int id);
	
}
