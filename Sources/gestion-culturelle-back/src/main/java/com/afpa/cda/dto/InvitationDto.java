package com.afpa.cda.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvitationDto {

	private int id;
	
	private ManifestationDto manifestation;
	private UserDto vip;
	
	String reponse;
	Date dateInvitation;
	
}
