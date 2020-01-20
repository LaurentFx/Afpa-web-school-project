package com.afpa.cda.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.AnimateurDto;
@Service
public class AnimateurServiceImpl implements IAnimateurService {
    @Autowired
    private UserRepository animateurRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public List<AnimateurDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AnimateurDto add(AnimateurDto anim) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateSportif(AnimateurDto anim, int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteSportif(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AnimateurDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}