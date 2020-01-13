package com.afpa.cda.service;

import java.util.List;
import com.afpa.cda.dto.AnimateurDto;

public interface IAnimateurService {
	
	List<AnimateurDto> findAll();

	AnimateurDto add(AnimateurDto client);

	boolean updateSportif(AnimateurDto sportif, int id);

	boolean deleteSportif(int id);

	AnimateurDto findById(int id);

}
