package com.afpa.cda.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dto.AnimationDto;
@Service
public class AnimationServiceImpl implements IAnimationService {
    @Autowired
    private AnimationRepository animationRepository;
    @Autowired
    private ModelMapper modelMapper;

	@Override
	public AnimationDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AnimationDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}   

    
	@Override
	public AnimationDto add(AnimationDto anim) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(AnimationDto anim, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}