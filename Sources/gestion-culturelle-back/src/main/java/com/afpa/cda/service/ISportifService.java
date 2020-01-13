package com.afpa.cda.service;

import java.util.List;
import com.afpa.cda.dto.SportifDto;

public interface ISportifService {
	
	List<SportifDto> findAll();

	SportifDto add(SportifDto client);

	boolean updateSportif(SportifDto sportif, int id);

	boolean deleteSportif(int id);

	SportifDto findById(int id);

}
