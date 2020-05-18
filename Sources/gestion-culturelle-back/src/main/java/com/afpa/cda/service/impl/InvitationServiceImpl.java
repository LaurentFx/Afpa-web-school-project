package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.UserDto;
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
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> findAll() {
		return this.userRepository.findAll().stream().map(v -> {
			UserDto userDto = new UserDto();
			userDto.setId(v.getId());
			userDto.setNom(v.getNom());
			userDto.setPrenom(v.getPrenom());

			RoleDto rolDto = new RoleDto();
			rolDto.setId(v.getRole().getId());
			rolDto.setLabel(v.getRole().getLabel());
			userDto.setRole(rolDto);

			userDto.setEntreprise(v.getEntreprise());
			return userDto;
		}).collect(Collectors.toList());
	}

	// @Override
	// public List <UserDto> findByRole (int id) {
	// return this.userRepository.findByRoleId(id);
	//
	// }

	@Override
	public List<UserDto> findAllVipsByManifestation(int id) {
		System.out.println("test methode findAllVipsByManifestation ");
		Optional<Manifestation> manifOp = this.manifestationRepository.findById(id);
		List<UserDto> listVipsByManifestation = new ArrayList<UserDto>();
		if (manifOp.isPresent()) {
			Manifestation manifestation = manifOp.get();
			for (User user : manifestation.getListVips()) {
				System.out.println("test methode findAllVipsByManifestation 2");
				listVipsByManifestation.add(UserDto.builder().id(user.getId())
						.nom(user.getNom()).prenom(user.getPrenom()).entreprise(user.getEntreprise()).build());
			}
		}
		return listVipsByManifestation;
	}

	@Override
	public List<UserDto> findByRole(int id) {
		
		List<User> listUsers = this.userRepository.findAll();
		System.out.println("test methode findByRole ");
		List<UserDto> listByRole = new ArrayList<UserDto>();

		for (User user : listUsers) {
			if (user.getRole().getId() == 5) {
				System.out.println("test methode findByRole 2");
				UserDto userDto = UserDto.builder().id(user.getId())
						.nom(user.getNom()).prenom(user.getPrenom()).entreprise(user.getEntreprise()).build();
//				UserDto userDto = this.modelMapper.map(user, UserDto.class);
//				userDto.setPassword(null);
//				userDto.setTokenSecret(null);
//				userDto.setRole(this.modelMapper.map(user.getRole(), RoleDto.class));
				listByRole.add(userDto);
			}
		}
		return listByRole;
	}

	@Override
	public UserDto add(UserDto userDto) {
		User user = this.userRepository.save(this.modelMapper.map(userDto, User.class));
		userDto.setId(user.getId());
		return userDto;
	}

	@Override
	public UserDto findById(int id) {
		Optional<User> userOp = this.userRepository.findById(id);
		UserDto userDto = null;
		if (userOp.isPresent()) {
			User user = userOp.get();
			userDto = this.modelMapper.map(user, UserDto.class);
		}
		return userDto;
	}

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

			// this.userRepository.save(this.modelMapper.map(user,User.class));
			return true;
		}
		return false;
	}
	
	
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

			// this.userRepository.save(this.modelMapper.map(user,User.class));
			return true;
		}
		return false;
	}
	

	@Override
	public boolean delete(int id) {
		if (this.userRepository.existsById(id)) {
			this.userRepository.deleteById(id);
			System.err.println("user supprimé");
			return true;
		}
		return false;
	}

}