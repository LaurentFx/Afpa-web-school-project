package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;

public interface IAnimationService {
	
	List<AnimationDto> findAll();

	boolean add(AnimationDto anim);

	boolean update(AnimationDto anim, int id);

	boolean delete(int id);

	AnimationDto findById(int id);

	ManifestationDto calcul(ManifestationDto manifDto);

}
