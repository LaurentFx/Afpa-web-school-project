package com.afpa.cda.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.AnimateurDto;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IAnimateurService;
@Service
public class AnimateurServiceImpl implements IAnimateurService {

	@Autowired
	private UserRepository animateurRepository;
	
	@Autowired
	private AnimationRepository animationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AnimateurDto> findAll() {

		return this.animateurRepository.findAll()
				
				.stream()
				.map(an-> {
					AnimateurDto animateurDto = new AnimateurDto();
					animateurDto.setId(an.getId());
					animateurDto.setNom(an.getNom());
					animateurDto.setPrenom(an.getPrenom());					
					animateurDto.setEmail(an.getEmail());
					animateurDto.setAdresse(an.getAdresse());				
					animateurDto.setEntreprise(an.getEntreprise());
				

					animateurDto.setAnimations(new ArrayList<AnimationDto>());

					for (Animation anim : an.getAnimations()) {	
						animateurDto.getAnimations()
								.add(AnimationDto
										.builder().id(anim.getId())
										.id(anim.getId())
										.label(anim.getLabel())
										.type(anim.getType())
										.prix(anim.getPrix())
										.nbreSpectateursPrevus(anim.getNbreSpectateursPrevus())
										.build());
								
											
					}
					
					RoleDto roleDto = new RoleDto();
					roleDto.setLabel(an.getRole().getLabel());
					animateurDto.setRole(roleDto);
				
				return animateurDto;

					
				})

				.collect(Collectors.toList());
				
				/* List<User> liste_Animateurs= this.animateurRepository.findByRole("ANIM");
				 
			 	
				 for(User user: liste_Animateurs ) {			 
					 
					 	AnimateurDto animDto = new AnimateurDto();	
					 	
						animDto.setId(user.getId());
						animDto.setNom(user.getNom());
						animDto.setEmail(user.getEmail());			
						animDto.setAdresse(user.getAdresse());

									
					ArrayList<AnimationDto> list_Animations = new ArrayList<AnimationDto>();
					
					animDto.setAnimations(new ArrayList<AnimationDto>());			
						for (AnimationDto animation: list_Animations) {
							         animDto.getAnimations()
									.add(AnimationDto
											.builder().id(animation.getId())
											.label(animation.getLabel())								
											.build());
						}
						
						
				 }
				return null;
			
			}*/

	}
	@Override
	public AnimateurDto add(AnimateurDto anim) {
		User perE = this.animateurRepository.save
				(this.modelMapper.map(anim, User.class));
		anim.setId(perE.getId());
		return anim;
	}
	
	@Override
	public boolean updateAnimateur(AnimateurDto anim, int id) {
		Optional<User> perE = this.animateurRepository.findById(id);
		if (perE.isPresent()) {
			this.animateurRepository.save(this.modelMapper.map(anim,User.class));
			return true;
		}
		return false;
	}


	@Override
	public boolean deleteAnimateur(int id) {
		if (this.animateurRepository.existsById(id)) {
			this.animateurRepository.deleteById(id);
			System.err.println("animateur supprimé");
			return true;
		}
		return false;
	}
	@Override
	public AnimateurDto findById(int id) {
		Optional<User> perE = this.animateurRepository.findById(id);
		AnimateurDto animateurDto = null;
		if (perE.isPresent()) {
			User pr = perE.get();
			animateurDto = this.modelMapper.map(pr, AnimateurDto.class);
		}
		return animateurDto;
	}


}