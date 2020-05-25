package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.InvitationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.InvitationDto;
import com.afpa.cda.dto.ManifestationDto;
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

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<InvitationDto> findAll() {
		return this.invitationRepository.findAll().stream().map(i -> {
			InvitationDto invitationDto = new InvitationDto();

			invitationDto.setId(i.getId());

			ManifestationDto manifestationDto = new ManifestationDto();
			manifestationDto.setId(i.getManifestation().getId());
			manifestationDto.setLabel(i.getManifestation().getLabel());
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
		System.out.println("test methode findInvitationByUserId ");

		List<InvitationDto> listInvitationByVip =this.invitationRepository
				.findInvitationByUserId(id)
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
					vipDto.setEmail(i.getVip().getEmail());
					invitationDto.setVip(vipDto);

					invitationDto.setReponse(i.getReponse());
					invitationDto.setDateInvitation(i.getDateInvitation());

					return invitationDto;
				}).collect(Collectors.toList());

		//		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		//		List<UserDto> listVipsByManifestation = new ArrayList<UserDto>();
		//		if (manifOp.isPresent()) {
		//			Manifestation manifestation = manifOp.get();
		//			for (User user : manifestation.getListVips()) {
		//				System.out.println("test methode findAllVipsByManifestation 2");
		//				listVipsByManifestation.add(UserDto.builder().id(user.getId())
		//						.nom(user.getNom()).prenom(user.getPrenom())
		//						.entreprise(user.getEntreprise()).email(user.getEmail()).build());
		//			}
		//		}

		return listInvitationByVip;
	}

	@Override
	public List<InvitationDto> findInvitationByManifestationId(int id) {

		System.out.println("test methode findManifestationByUser ");

		List<InvitationDto> listInvitationByManifestation =this.invitationRepository
				.findInvitationByManifestationId(id)
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
					vipDto.setEmail(i.getVip().getEmail());
					invitationDto.setVip(vipDto);

					invitationDto.setReponse(i.getReponse());
					invitationDto.setDateInvitation(i.getDateInvitation());

					return invitationDto;
				}).collect(Collectors.toList());

		return listInvitationByManifestation;
	}

	// inutile
	//	@Override
	//	public List<UserDto> findByRole(int id) {
	//		// A tester
	//		//	List<UserDto> listUsers = this.userRepository.findByRoleId(5);
	//
	//		List<User> listUsers = this.userRepository.findAll();
	//		System.out.println("test methode findByRole ");
	//		List<UserDto> listByRole = new ArrayList<UserDto>();
	//
	//		for (User user : listUsers) {
	//			if (user.getRole().getId() == 5) {
	//				System.out.println("test methode findByRole 2");
	//				UserDto userDto = UserDto.builder().id(user.getId())
	//						.nom(user.getNom()).prenom(user.getPrenom()).entreprise(user.getEntreprise()).build();
	//				//				UserDto userDto = this.modelMapper.map(user, UserDto.class);
	//				//				userDto.setPassword(null);
	//				//				userDto.setTokenSecret(null);
	//				//				userDto.setRole(this.modelMapper.map(user.getRole(), RoleDto.class));
	//				listByRole.add(userDto);
	//			}
	//		}
	//		return listByRole;
	//	}

	@Override
	public boolean add(InvitationDto invitationDto) {

		Optional <Invitation> invitationOp = this.invitationRepository
				.findByUserAndManifestation(invitationDto.getVip().getId(), invitationDto.getManifestation().getId());
		if (!invitationOp.isPresent()) {

			Invitation invitation = new Invitation();

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

			invitation.setReponse("NC");
			invitation.setDateInvitation(invitationDto.getDateInvitation());

			this.invitationRepository.save(invitation);

			return false;
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

	// Tester si utile
	@Override
	public boolean updateAdd(ManifestationDto manifestationDto, int id) {
		Optional<User> vipOp = this.userRepository.findById(id);

		if (vipOp.isPresent()) {
			Optional<Manifestation> manifestationOp = this.manifestationRepository.findById(manifestationDto.getId());

			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				List<User> listVips = manifestation.getListVips();
				if(listVips == null) {
					listVips = new ArrayList<>();
				}
				listVips.add(vipOp.get());
				manifestation.setReservationsVip(manifestation.getReservationsVip()-1);
				System.out.println(manifestation.getReservationsVip());
				this.manifestationRepository.save(manifestation);
			}
			return true;
		}
		return false;
	}

	// Tester si utile
	@Override
	public boolean updateSub(ManifestationDto manifestationDto, int id) {
		Optional<User> vipOp = this.userRepository.findById(id);

		if (vipOp.isPresent()) {
			Optional<Manifestation> manifestationOp = this.manifestationRepository.findById(manifestationDto.getId());

			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				List<User> listVips = manifestation.getListVips();
				if(listVips == null) {
					listVips = new ArrayList<>();
				}
				listVips.remove(vipOp.get());
				manifestation.setReservationsVip(manifestation.getReservationsVip()+1);
				System.out.println(manifestation.getReservationsVip());
				this.manifestationRepository.save(manifestation);
			}

			return true;
		}
		return false;
	}


	@Override
	public boolean delete(int id) {
		if (this.invitationRepository.existsById(id)) {
			this.invitationRepository.deleteById(id);
			System.err.println("invitation supprimée");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAll(int id) {
		List <Invitation> listInvitations = this.invitationRepository.findInvitationByManifestationId(id);

		if (!listInvitations.isEmpty()) {
			for (Invitation invitation : listInvitations) {
				if (this.invitationRepository.existsById(invitation.getId())) {
					this.invitationRepository.deleteById(invitation.getId());
				}
			} 
			return true;
		}
		return false;
	}


}