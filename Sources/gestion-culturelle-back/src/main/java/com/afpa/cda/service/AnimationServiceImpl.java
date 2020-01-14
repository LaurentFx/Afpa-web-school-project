package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.entity.Animation;
@Service
public class AnimationServiceImpl implements IAnimationService {
    @Autowired
    private AnimationRepository animationRepository;
    @Autowired
    private ModelMapper modelMapper;

	@Override
	public AnimationDto findById(int id) {
		Optional<Animation> animOp = this.animationRepository.findById(id);
		AnimationDto animDto = null;
		if (animOp.isPresent()) {
			Animation an = animOp.get();
			animDto = this.modelMapper.map(an,AnimationDto.class);
		}
		return animDto;
	}
	@Override
	public List<AnimationDto> findAll() {
		
		return this.animationRepository.findAll()
				.stream()
				.map(a-> this.modelMapper.map(a,AnimationDto.class))
				.collect(Collectors.toList());
				
	}   

    
	@Override
	public AnimationDto add(AnimationDto anim) {
		Animation animE = this.modelMapper.map(anim,Animation.class);
		Animation animationEntity = this.animationRepository.save(animE);
		anim.setId(animationEntity .getId());
		return anim;
	}
	@Override
	public boolean update(AnimationDto anim, int id) {
		Optional<Animation> animEU = this.animationRepository.findById(id);
		if (animEU.isPresent()) {
			Animation an = animEU.get();
			an.setLabel(anim.getLabel());
			an.setType(anim.getType());
			an.setPrix(anim.getPrix());
			an.setNbreSpectateursPrevus(anim.getNbreSpectateursPrevus());
			this.animationRepository.save(an);
			return true;
		}
		return false;
	}
	@Override
	public boolean delete(int id) {
		if(this.animationRepository.existsById(id)) {
			this.animationRepository.deleteById(id);
			return true;
		}
		return false;
	}
}