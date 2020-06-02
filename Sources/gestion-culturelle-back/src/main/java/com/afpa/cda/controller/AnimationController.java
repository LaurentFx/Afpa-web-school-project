package com.afpa.cda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping(path = "/public/animation")
	public List<AnimationDto> getAll(){
		return this.animationService.findAll();
	}
	
	@GetMapping(path = "/animation/{id}")
	public AnimationDto getOne(@PathVariable int id) {
		return this.animationService.findById(id);
	}
	
	@GetMapping(path = "/animation/purpose")
	public List<AnimationDto> getAnimations() {
		return this.animationService.findAnimationsToPurpose();
	}
	
	//@PreAuthorize("hasAnyAuthority('ANIM')")
	@PostMapping(path = "/animation")
	public int add(@RequestBody AnimationDto anim) {
		return this.animationService.add(anim);
	}
	
	@PutMapping(path="/animation/{id}")
	public boolean update(@RequestBody AnimationDto anim, @PathVariable int id) {
		return this.animationService.update(anim,id);
	}
	
	@DeleteMapping(path="/animation/{id}")
	public boolean delete(@PathVariable int id) {
		return this.animationService.delete(id);
	}
	
}
