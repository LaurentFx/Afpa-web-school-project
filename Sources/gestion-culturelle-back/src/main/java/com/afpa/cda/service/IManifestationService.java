package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ManifestationDto;



public interface IManifestationService {
	
	List<ManifestationDto> findAll();
	
	ManifestationDto findById(int id);
	
	public boolean findAvailability (ManifestationDto manifestationDto);

	boolean add(ManifestationDto manifestationDto);

	boolean update(ManifestationDto manifestationDto, int id);

	boolean delete(int id);

	ManifestationDto calcul(ManifestationDto manifestationDto);

}
