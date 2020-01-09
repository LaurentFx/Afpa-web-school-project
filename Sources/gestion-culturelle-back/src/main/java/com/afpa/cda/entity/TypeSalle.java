package com.afpa.cda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class TypeSalle {
	@Id
	@GeneratedValue(generator = "TYPESALLE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(unique=true)
	private String label;
	

}
