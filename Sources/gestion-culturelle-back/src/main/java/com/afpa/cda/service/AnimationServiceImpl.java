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
		Optional<Animation> animationOp=this.animationRepository.findById(id);
		AnimationDto animat = null;
		if(animationOp.isPresent()) {
			Animation anim = animationOp.get();
			animat = this.modelMapper.map(anim,AnimationDto.class);
		}
		return animat;
	}


	@Override
	public List<AnimationDto> findAll() {
		return this.animationRepository.findAll()
				.stream()
				.map(a->AnimationDto.builder()
						.id(a.getId())
						.label(a.getLabel())
						.type(a.getType())
						.prix(a.getPrix())
						.nbreSpectateursPrevus(a.getNbreSpectateursPrevus())
						.build())
				.collect(Collectors.toList());
	}   


	@Override
	public AnimationDto add(AnimationDto anim) {
		try {
			Animation animat = this.animationRepository.save(this.modelMapper.map(anim,Animation.class));
			anim.setId(animat.getId());
			System.err.println("Animation ajoutee");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		return anim;
	}


	@Override
	public boolean update(AnimationDto anim, int id) {
		Optional <Animation> animOp = this.animationRepository.findById(id);
		if (animOp.isPresent()) {
			Animation animat = animOp.get();
			animat.setLabel(anim.getLabel());
			animat.setType(anim.getType());
			animat.setPrix(anim.getPrix());
			animat.setNbreSpectateursPrevus(anim.getNbreSpectateursPrevus());
			this.animationRepository.save(animat);
			System.err.println("Animation mise à jour");
			return true;

		}
		return false;
	}


	@Override
	public boolean delete(int id) {
		if (this.animationRepository.existsById(id)) {
			this.animationRepository.deleteById(id);
			System.err.println("Animation supprimée");
			return true;
		}
		return false;
	}
}