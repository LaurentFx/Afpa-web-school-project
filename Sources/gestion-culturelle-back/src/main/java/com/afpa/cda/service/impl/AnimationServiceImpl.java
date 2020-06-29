package com.afpa.cda.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IAnimationService;
import com.afpa.cda.service.IManifestationService;
@Service
public class AnimationServiceImpl implements IAnimationService {
	@Autowired
	private AnimationRepository animationRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ManifestationRepository manifestationRepository;

	@Autowired
	private IManifestationService manifestationService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AnimationDto> findAll() {

		return this.animationRepository.findAll()
				.stream()
				.map(a-> this.modelMapper.map(a,AnimationDto.class))
				.collect(Collectors.toList());
	}  

	@Override
	public AnimationDto findById(int id) {

		Optional<Animation> animOp = this.animationRepository.findById(id);
		AnimationDto animationDto = null;
		if (animOp.isPresent()) {
			Animation an = animOp.get();
			animationDto = this.modelMapper.map(an,AnimationDto.class);
		}
		return animationDto;
	}

	@Override
	public List<AnimationDto> findAnimationsToPurpose() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List <AnimationDto> listAnimations = findAll();
		List <ManifestationDto> listManifestations = this.manifestationService.findAll();
		List <AnimationDto> listAnimationsToPurpose = new ArrayList<AnimationDto> (listAnimations);

		for (AnimationDto  animationDto : listAnimations) {
			for (ManifestationDto manifestationDto : listManifestations) {
				if (animationDto.getId()==manifestationDto.getAnimation().getId()) {
					listAnimationsToPurpose.remove(animationDto);
				}
			}
		}
		return listAnimationsToPurpose;
	}

	@Override
	public int add(AnimationDto animationDto) {
		int id=0;
		Optional <Animation> animationOp = this.animationRepository.findAnimationByLabel(animationDto.getLabel());

		if (!animationOp.isPresent()) {
			Animation animation = new Animation();

			animation.setLabel(animationDto.getLabel());
			animation.setType(animationDto.getType());
			animation.setPrix(animationDto.getPrix());
			animation.setNbreSpectateursPrevus(animationDto.getNbreSpectateursPrevus());

			Optional <User> animateurOp = this.userRepository.findById(animationDto.getAnimateur().getId());
			if (animateurOp.isPresent()) {
				User animateur = animateurOp.get();
				animation.setAnimateur(animateur);
			}
			id=this.animationRepository.save(animation).getId();
			return id;
		}

		return id;
	}


	@Override
	public boolean update(AnimationDto animationDto, int id) {
		Optional<Animation> animationOp = this.animationRepository.findById(id);
		if (animationOp.isPresent()) {
			Animation animation = animationOp.get();
			animation.setLabel(animationDto.getLabel());
			animation.setType(animationDto.getType());
			animation.setPrix(animationDto.getPrix());
			animation.setNbreSpectateursPrevus(animationDto.getNbreSpectateursPrevus());

			Optional <User> animateurOp = this.userRepository.findById(animationDto.getAnimateur().getId());
			if (animateurOp.isPresent()) {
				User animateur = animateurOp.get();
				animation.setAnimateur(animateur);
			}

			this.animationRepository.save(animation);

			List<Manifestation> listManifestations = this.manifestationRepository.findManifestationByAnimation(id);
			if (!listManifestations.isEmpty()) {
				for (Manifestation manifestation : listManifestations) {
					if (manifestation.getSalle()!=null&&manifestation.getDateDebut()!=null&&manifestation.getDateFin()!=null){
						ManifestationDto manifestationDto = modelMapper.map(manifestation,ManifestationDto.class);
						manifestationDto=manifestationService.calcul(manifestationDto);
						Manifestation manif = modelMapper.map(manifestationDto,Manifestation.class);

						Optional<Manifestation> manifOp = this.manifestationRepository.findById(manifestation.getId());
						if (manifOp.isPresent()) {
							this.manifestationRepository.save(manif);
						}
					}
				}
			}
			return true;
		}
		return false;
	}


	@Override
	public boolean delete(int id) {
		List <Manifestation> listManifestations = this.manifestationRepository.findManifestationByAnimation(id);

		if (listManifestations.isEmpty() && this.animationRepository.existsById(id))		 {
			this.animationRepository.deleteById(id);
			return true;
		}
		return false;
	}
}