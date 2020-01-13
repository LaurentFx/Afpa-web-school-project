package com.afpa.cda.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Animation {
	
	@Id
	@GeneratedValue(generator = "ANIMATION_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
//	@OneToMany
//	@Column(nullable = true)
	private String label;
//	
//	@Column(nullable = true)	
//	private String type_animation;
//	
//	@Column(nullable = true)
//	private double cout;
//	
//	@Column(nullable = true)	
//	private int prevision_Spectateurs;

}
