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
	
	private String label;
	
	private String type;
	
	private double prix;
	
	private int nbreSpectateursPrevus;

}
