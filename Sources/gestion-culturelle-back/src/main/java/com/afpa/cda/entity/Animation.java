package com.afpa.cda.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Animation {
	
	@Id
	@GeneratedValue(generator = "ANIMATION_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@OneToMany
	@Column(nullable = true)
	private String label_animation;
	
	@Column(nullable = true)	
	private String type_animation;
	
	@Column(nullable = true)
	private double cout;
	
	@Column(nullable = true)	
	private int prevision_Spectateurs;

}
