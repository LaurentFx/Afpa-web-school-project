package com.afpa.cda.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
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
	public List<AnimationDto> findAll() {

		return this.animationRepository.findAll()
				.stream()
				.map(a-> this.modelMapper.map(a,AnimationDto.class))
				.collect(Collectors.toList());

	}   


	@Override
	public boolean add(AnimationDto animationDto) {
		Optional <Animation> animationOp = this.animationRepository.findByLabel(animationDto.getLabel());

		if (!animationOp.isPresent()) {
			this.animationRepository.save(this.modelMapper.map(animationDto,Animation.class));
			return false;
		}

		return true;
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
			this.animationRepository.save(animation);

			List<Manifestation> listManifestations = manifestationRepository.findManifestationByAnimationId(id);
			if (!listManifestations.isEmpty()) {
				for (Manifestation manifestation : listManifestations) {
					//if (manifestation.getAnimation().getId()==animation.getId()) {
					ManifestationDto manifestationDto = modelMapper.map(manifestation,ManifestationDto.class);
					manifestationDto=manifestationService.calcul(manifestationDto);
					Manifestation manif = modelMapper.map(manifestationDto,Manifestation.class);

					Optional<Manifestation> manifOp = this.manifestationRepository.findById(manifestation.getId());
					if (manifOp.isPresent()) {
						this.manifestationRepository.save(manif);
					}
				}
			}

			return true;
		}
		return false;
	}


	@Override
	public boolean delete(int id) {
		List <Manifestation> listManifestations = manifestationRepository.findManifestationByAnimationId(id);

		if (listManifestations.isEmpty() && this.animationRepository.existsById(id))		 {
			this.animationRepository.deleteById(id);
			return true;
		}
		//		if(this.animationRepository.existsById(id)) {
		//			this.animationRepository.deleteById(id);
		//			return true;
		//		}
		return false;
	}
}