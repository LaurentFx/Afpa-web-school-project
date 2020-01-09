package com.afpa.cda.service;

import java.util.List;
import com.afpa.cda.dto.ArtisteDto;

public interface IArtisteService {
	
	List<ArtisteDto> findAll();

	ArtisteDto add(ArtisteDto artiste);

	boolean updateArtiste(ArtisteDto artiste, int id);

	boolean deleteArtiste(int id);

	ArtisteDto findById(int id);

}
