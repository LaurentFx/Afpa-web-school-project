package com.afpa.cda.service;

import java.util.List;


import com.afpa.cda.dto.PanierDto;



public interface IPanierService {
	
	List<PanierDto> findAll();

	PanierDto add(PanierDto panier);

	boolean updatePanier(PanierDto panier, int id);

	boolean deletePanier(int id);

	PanierDto findById(int id);

}
