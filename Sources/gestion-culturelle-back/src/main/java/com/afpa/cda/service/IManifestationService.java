package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ManifestationDto;



public interface IManifestationService {
	
	List<ManifestationDto> findAll();
	
	public boolean findAvailability (ManifestationDto manifestationDto);

	boolean add(ManifestationDto manif);

	boolean update(ManifestationDto manif, int id);

	boolean delete(int id);

	ManifestationDto findById(int id);

	ManifestationDto calcul(ManifestationDto manifDto);

}
