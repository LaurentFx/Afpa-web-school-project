package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ManifestationDto;



public interface IManifestationService {
	
	List<ManifestationDto> findAll();
	
	public boolean findAvailability (ManifestationDto manifestationDto);

	ManifestationDto add(ManifestationDto manif);

	boolean updateManifestation(ManifestationDto manif, int id);

	boolean deleteManifestation(int id);

	ManifestationDto findById(int id);

	ManifestationDto calcul(ManifestationDto manifDto);

}
