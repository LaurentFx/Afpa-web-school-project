package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.InvitationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.InvitationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Invitation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IInvitationService;

@Service
public class InvitationServiceImpl implements IInvitationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ManifestationRepository manifestationRepository;

	@Autowired
	private InvitationRepository invitationRepository;

	@Override
	public List<InvitationDto> findAll() {
		return this.invitationRepository.findAll().stream().map(i -> {
			InvitationDto invitationDto = new InvitationDto();

			invitationDto.setId(i.getId());

			ManifestationDto manifestationDto = new ManifestationDto();
			manifestationDto.setId(i.getManifestation().getId());
			manifestationDto.setLabel(i.getManifestation().getLabel());
			manifestationDto.setDateDebut(i.getManifestation().getDateDebut());
			manifestationDto.setDateFin(i.getManifestation().getDateFin());
			manifestationDto.setReservationsVip(i.getManifestation().getReservationsVip());

			SalleDto salleDto = new SalleDto();
			salleDto.setId(i.getManifestation().getSalle().getId());
			salleDto.setLabel(i.getManifestation().getSalle().getLabel());
			manifestationDto.setSalle(salleDto);

			invitationDto.setManifestation(manifestationDto);

			UserDto vipDto = new UserDto();
			vipDto.setId(i.getVip().getId());
			vipDto.setNom(i.getVip().getNom());
			vipDto.setEmail(i.getVip().getEmail());
			invitationDto.setVip(vipDto);

			invitationDto.setReponse(i.getReponse());
			invitationDto.setDateInvitation(i.getDateInvitation());

			return invitationDto;
		}).collect(Collectors.toList());
	}

	@Override
	public InvitationDto findById(int id) {
		Optional<Invitation> invitationOp = this.invitationRepository.findById(id);
		InvitationDto invitationDto = new InvitationDto();
		if (invitationOp.isPresent()) {

			Invitation invitation = invitationOp.get();

			invitationDto.setId(invitation.getId());

			ManifestationDto manifestationDto = new ManifestationDto();
			manifestationDto.setId(invitation.getManifestation().getId());
			manifestationDto.setLabel(invitation.getManifestation().getLabel());
			manifestationDto.setDateDebut(invitation.getManifestation().getDateDebut());
			manifestationDto.setDateFin(invitation.getManifestation().getDateFin());
			manifestationDto.setReservationsVip(invitation.getManifestation().getReservationsVip());

			SalleDto salleDto = new SalleDto();
			salleDto.setId(invitation.getManifestation().getSalle().getId());
			salleDto.setLabel(invitation.getManifestation().getSalle().getLabel());
			manifestationDto.setSalle(salleDto);

			invitationDto.setManifestation(manifestationDto);

			UserDto vipDto = new UserDto();
			vipDto.setId(invitation.getVip().getId());
			vipDto.setNom(invitation.getVip().getNom());
			vipDto.setEmail(invitation.getVip().getEmail());
			invitationDto.setVip(vipDto);

			invitationDto.setReponse(invitation.getReponse());
			invitationDto.setDateInvitation(invitation.getDateInvitation());

		}
		return invitationDto;
	}

	@Override
	public List<InvitationDto> findInvitationByUserId(int id) {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<InvitationDto> listInvitationByVip =this.invitationRepository
				.findInvitationByUser(id)
				.stream().map(i-> {
					InvitationDto invitationDto = new InvitationDto();
					invitationDto.setId(i.getId());

					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(i.getManifestation().getId());
					manifestationDto.setLabel(i.getManifestation().getLabel());
					manifestationDto.setDateDebut(i.getManifestation().getDateDebut());
					manifestationDto.setDateFin(i.getManifestation().getDateFin());
					manifestationDto.setReservationsVip(i.getManifestation().getReservationsVip());

					SalleDto salleDto = new SalleDto();
					salleDto.setId(i.getManifestation().getSalle().getId());
					salleDto.setLabel(i.getManifestation().getSalle().getLabel());
					manifestationDto.setSalle(salleDto);

					invitationDto.setManifestation(manifestationDto);

					UserDto vipDto = new UserDto();
					vipDto.setId(i.getVip().getId());
					vipDto.setNom(i.getVip().getNom());
					vipDto.setPrenom(i.getVip().getPrenom());
					vipDto.setEmail(i.getVip().getEmail());
					invitationDto.setVip(vipDto);

					invitationDto.setReponse(i.getReponse());
					invitationDto.setDateInvitation(i.getDateInvitation());

					return invitationDto;
				}).collect(Collectors.toList());

		return listInvitationByVip;
	}

	@Override
	public List<InvitationDto> findNewInvitationByUserId(int id) {

		List<InvitationDto> listInvitations= findInvitationByUserId(id);
		List<InvitationDto> listNewInvitations= new ArrayList<InvitationDto>();
		
		for (InvitationDto invitationDto : listInvitations) {
			if (invitationDto.getReponse().equalsIgnoreCase("NC")) {
				listNewInvitations.add(invitationDto);
			}
		}
		return listNewInvitations;
	}


	@Override
	public List<InvitationDto> findInvitationByManifestationId(int id) {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<InvitationDto> listInvitationByManifestation =this.invitationRepository
				.findInvitationByManifestation(id)
				.stream().map(i-> {

					InvitationDto invitationDto = new InvitationDto();
					invitationDto.setId(i.getId());

					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(i.getManifestation().getId());
					manifestationDto.setLabel(i.getManifestation().getLabel());
					invitationDto.setManifestation(manifestationDto);

					UserDto vipDto = new UserDto();
					vipDto.setId(i.getVip().getId());
					vipDto.setNom(i.getVip().getNom());
					vipDto.setPrenom(i.getVip().getPrenom());
					vipDto.setEmail(i.getVip().getEmail());
					invitationDto.setVip(vipDto);

					invitationDto.setReponse(i.getReponse());
					invitationDto.setDateInvitation(i.getDateInvitation());

					return invitationDto;
				}).collect(Collectors.toList());


		return listInvitationByManifestation;
	}


	@Override
	public boolean add(InvitationDto invitationDto) {

		Optional <Invitation> invitationOp = this.invitationRepository
				.findInvitationByUserAndManifestation(invitationDto.getVip().getId(), invitationDto.getManifestation().getId());
		if (!invitationOp.isPresent()) {

			Invitation invitation = new Invitation();

			Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(invitationDto.getManifestation().getId());
			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				if (manifestation.getReservationsVip()>0) {
					manifestation.setReservationsVip(manifestation.getReservationsVip()-1);
					this.manifestationRepository.save(manifestation);
					invitation.setManifestation(manifestation);

					Optional <User> vipOp = this.userRepository.findById(invitationDto.getVip().getId());
					if (vipOp.isPresent()) {
						User vip = vipOp.get();
						invitation.setVip(vip);
					}
					invitation.setReponse("NC");
					invitation.setDateInvitation(new Date());

					this.invitationRepository.save(invitation);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return false;
				}
			}
		}
		return true;
	}


	@Override
	public boolean update(InvitationDto invitationDto, int id) {

		Optional<Invitation> invitationOp = this.invitationRepository.findById(id);

		if (invitationOp.isPresent()) {
			Invitation invitation = invitationOp.get();

			Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(invitationDto.getManifestation().getId());
			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				invitation.setManifestation(manifestation);
			}

			Optional <User> vipOp = this.userRepository.findById(invitationDto.getVip().getId());
			if (vipOp.isPresent()) {
				User vip = vipOp.get();
				invitation.setVip(vip);
			}

			invitation.setReponse(invitationDto.getReponse());
			invitation.setDateInvitation(invitationDto.getDateInvitation());

			this.invitationRepository.save(invitation);
			return true;

		}
		return false;
	}

	@Override
	public boolean updateReponse(String reponse, int id) {
		Optional<Invitation> invitationOp = this.invitationRepository.findById(id);

		if (invitationOp.isPresent()) {
			Invitation invitation = invitationOp.get();
			invitation.setReponse(reponse);
			this.invitationRepository.save(invitation);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		if (this.invitationRepository.existsById(id)) {
			Optional<Invitation> invitationOp = this.invitationRepository.findById(id);
			if (invitationOp.isPresent()) {
				Invitation invitation = invitationOp.get();

				Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(invitation.getManifestation().getId());
				if (manifestationOp.isPresent()) {
					Manifestation manifestation = manifestationOp.get();
					manifestation.setReservationsVip(manifestation.getReservationsVip()+1);
					this.manifestationRepository.save(manifestation);
				}
			}

			this.invitationRepository.deleteById(id);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAll(int id) {
		List <Invitation> listInvitations = this.invitationRepository.findInvitationByManifestation(id);

		if (!listInvitations.isEmpty()) {
			int i=0;
			for (Invitation invitation : listInvitations) {
				if (this.invitationRepository.existsById(invitation.getId())) {
					this.invitationRepository.deleteById(invitation.getId());
					i++;
				}
			} 
			Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(id);
			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				manifestation.setReservationsVip(manifestation.getReservationsVip()+i);
				this.manifestationRepository.save(manifestation);
			}
			return true;
		}
		return false;
	}

}