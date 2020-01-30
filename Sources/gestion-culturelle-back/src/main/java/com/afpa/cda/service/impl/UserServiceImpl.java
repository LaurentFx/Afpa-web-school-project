package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dao.RoleRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Commande;
import com.afpa.cda.entity.Panier;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PanierRepository panierRepository;

	@Override
	public List<UserDto> findAll() {
		return this.UserRepository.findAll().stream().map(me -> {
			UserDto userDto = this.modelMapper.map(me, UserDto.class);
			// solution temporaire
			// ne pas remonter les mots de passe pour le service get
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setRole(this.modelMapper.map(me.getRole(), RoleDto.class));
			return userDto;
		}).collect(Collectors.toList());
	}


	@Override
	public List<UserDto> findByRole(int id) {
		List <User> listUsers =  this.UserRepository.findAll();

		List <UserDto> listByRole = new ArrayList<UserDto> ();

		for (User user : listUsers) {
			if (user.getRole().getId()==id && user.isInactif()) {
				UserDto userDto = this.modelMapper.map(user, UserDto.class);
				userDto.setPassword(null);
				userDto.setTokenSecret(null);
					userDto.setRole(this.modelMapper.map(user.getRole(), RoleDto.class));
				listByRole.add(userDto);
			}
		}
		return listByRole;
	}

	@Override
	public UserDto add(UserDto userDto) {
		User user = this.UserRepository.save(this.modelMapper.map(userDto, User.class));
		userDto.setId(user.getId());
		return userDto;
	}



	@Override
	public UserDto addClient(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		Optional<Role> roleOp=roleRepository.findById(4);
		if (roleOp.isPresent()) {
			user.setRole(roleOp.get());
		}
		user.setNumClient(userDto.getNom().substring(0,2)+"2020"+userDto.getPrenom().substring(0,2));
		Date dateDuJour = new Date();
		user.setPanier(Panier.builder()
				.dateValidation(dateDuJour)
				.total(0).build());
		user.getPanier().setListCommandes(new ArrayList<Commande>());
		user.setInactif(true);

		panierRepository.save(user.getPanier());
		user.setPanier(Panier.builder().id(user.getPanier().getId()).build());

		this.UserRepository.save(user);
		userDto = modelMapper.map(user, UserDto.class);
		userDto.setId(user.getId());

		return userDto;
	}

	@Override
	public Optional<UserDto> findById(Integer userId) {
		Optional<User> userEnOpt = this.UserRepository.findById(userId);
		UserDto userDto = new UserDto();
		if (userEnOpt.isPresent()) {
			User me = userEnOpt.get();
			userDto = this.modelMapper.map(me, UserDto.class);
			// solution temporaire
			// ne pas remonter les mots de passe pour le service get
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setRole(this.modelMapper.map(me.getRole(), RoleDto.class));
			return Optional.of(userDto);
		}
		return Optional.empty();
	}

	@Override
	public UserDto findOne(Integer userId) {
		Optional<User> userEnOpt = this.UserRepository.findById(userId);
		UserDto userDto = new UserDto();
		if (userEnOpt.isPresent()) {
			User me = userEnOpt.get();
			userDto = this.modelMapper.map(me, UserDto.class);
			// solution temporaire
			// ne pas remonter les mots de passe pour le service get
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setRole(this.modelMapper.map(me.getRole(), RoleDto.class));
		}
		return userDto;
	}


	@Override
	public boolean update(UserDto user, int id) {
		Optional<User> userOp = this.UserRepository.findById(id);
		if (userOp.isPresent()) {
			User userE = userOp.get();

			userE.setEmail(user.getEmail());
			userE.setAdresse(user.getAdresse());

			this.UserRepository.save(userE);

			// this.UserRepository.save(this.modelMapper.map(user,User.class));
			System.err.println("user mis à jour");
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(int id) {

		if (this.UserRepository.existsById(id)) {
			this.UserRepository.deleteById(id);
			System.err.println("user supprimé");
			return true;
		}
		return false;

	}



}



