package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.PanierDto;



public interface IPanierService {
	
	List<PanierDto> findAll();

	ManifestationDto add(PanierDto panier);

	boolean updateManifestation(PanierDto panier, int id);

	boolean deleteManifestation(int id);

	PanierDto findById(int id);

}
