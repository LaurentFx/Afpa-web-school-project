package com.afpa.cda.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(
		name="t_invitation"
)
public class Invitation {

	@Id
	@GeneratedValue(generator = "INVITATION_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "manifestation_id", nullable = false)
	Manifestation manifestation;
	
	@ManyToOne
	@JoinColumn(name = "vip_id", nullable = false)
	User vip;
	
	String reponse;
	
	Date dateInvitation;
	
}
