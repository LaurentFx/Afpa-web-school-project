package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.service.IAnimationService;

@RestController
public class AnimationController {

	@Autowired
	private IAnimationService animationService;
	
	@GetMapping(path = "/public/animation/list")
	public List<AnimationDto> getAll(){
		return this.animationService.findAll();
	}
	
	@PreAuthorize("hasAnyAuthority('RESP','ADMIN','ANIM','VIP','CLIENT')")
	@GetMapping(path = "/animation/show/{id}")
	public AnimationDto getOne(@PathVariable int id) {
		return this.animationService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP')")
	@GetMapping(path = "/animation/purpose")
	public List<AnimationDto> getAnimations() {
		return this.animationService.findAnimationsToPurpose();
	}
	
	@PreAuthorize("hasAnyAuthority('ANIM')")
	@PostMapping(path = "/animation/add")
	public int add(@RequestBody AnimationDto anim) {
		return this.animationService.add(anim);
	}
	
	@PreAuthorize("hasAnyAuthority('ANIM')")
	@PutMapping(path="/animation/update/{id}")
	public boolean update(@RequestBody AnimationDto anim, @PathVariable int id) {
		return this.animationService.update(anim,id);
	}
	
	@PreAuthorize("hasAnyAuthority('RESP','ADMIN')")
	@DeleteMapping(path="/animation/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return this.animationService.delete(id);
	}
	
}
