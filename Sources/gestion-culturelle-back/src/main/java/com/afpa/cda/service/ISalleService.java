package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.SalleDto;

public interface ISalleService {
	
	List<SalleDto> findAll();
	
	public List<SalleDto> findAllByCapacity(int id);
	
	int add(SalleDto sal);
	
	boolean update(SalleDto sal, int id);
	
	boolean delete (int id);

	SalleDto findById(int id);
	

}
