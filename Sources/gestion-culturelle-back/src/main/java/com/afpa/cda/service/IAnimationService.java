package com.afpa.cda.service;

import java.util.List;

import com.afpa.cda.dto.AnimationDto;

public interface IAnimationService {
	
	List<AnimationDto> findAll();

	int add(AnimationDto anim);

	boolean update(AnimationDto anim, int id);

	boolean delete(int id);

	AnimationDto findById(int id);

	List<AnimationDto> findAnimationsToPurpose();

}
