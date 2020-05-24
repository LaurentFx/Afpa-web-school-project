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
import com.afpa.cda.dto.PanierDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Article;
import com.afpa.cda.entity.Panier;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PanierRepository panierRepository;

	@Override
	public List<UserDto> findAll() {

		return this.userRepository.findAll().stream().map(user -> {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setNom(user.getNom());
			userDto.setPrenom(user.getPrenom());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setNumClient(null);
			userDto.setPanier(null);
			userDto.setAdresse(user.getAdresse());
			userDto.setEntreprise(user.getEntreprise());


			RoleDto roleDto = new RoleDto();
			if (user.getRole()!=null) {
				roleDto.setId(user.getRole().getId());
				roleDto.setLabel(user.getRole().getLabel());
			}
			userDto.setRole(roleDto);

			return userDto;
		})
				.collect(Collectors.toList());
	}

	@Override
	public List<UserDto> findByRole(int id) {
		// A tester
		//	List<UserDto> listUsers = this.userRepository.findByRoleId(id);

		List <User> listUsers =  this.userRepository.findAll();

		List <UserDto> listByRole = new ArrayList<UserDto> ();

		for (User user : listUsers) {
			if (user.getRole().getId()==id) {
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
	public boolean add(UserDto userDto) {
		Optional <User> userOp = this.userRepository.findByNomAndPrenom(userDto.getNom(), userDto.getPrenom());
		if (!userOp.isPresent()) {
			this.userRepository.save(this.modelMapper.map(userDto, User.class));
			return false;
		}

		return true;
	}

	@Override
	public UserDto addClient(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		Optional<Role> roleOp=roleRepository.findById(4);
		if (roleOp.isPresent()) {
			user.setRole(roleOp.get());
		}
		user.setNumClient(userDto.getNom().substring(0,1)+userDto.getId()+userDto.getPrenom().substring(0,1)+"2020");
		Date dateDuJour = new Date();
		user.setPanier(Panier.builder()
				.dateValidation(dateDuJour)
				.total(0).build());
	//	user.getPanier().setListArticles(new ArrayList<Article>());
		//	user.setInactif(true);

		panierRepository.save(user.getPanier());
		user.setPanier(Panier.builder().id(user.getPanier().getId()).build());

		this.userRepository.save(user);
		userDto = modelMapper.map(user, UserDto.class);
		userDto.setId(user.getId());

		return userDto;
	}

	@Override
	public Optional<UserDto> findByCurrentUser(Integer userId) {
		Optional<User> userOpt = this.userRepository.findById(userId);
		UserDto userDto = new UserDto();
		if (userOpt.isPresent()) {
			User user = userOpt.get();
		
			userDto.setId(user.getId());
			userDto.setNom(user.getNom());
			userDto.setPrenom(user.getPrenom());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setNumClient(user.getNumClient());
			
			PanierDto panierDto = new PanierDto();
			if (user.getPanier()!=null) {
				panierDto.setId(user.getPanier().getId());
				panierDto.setDateValidation(user.getPanier().getDateValidation());
				panierDto.setTotal(user.getPanier().getTotal());
			}
			userDto.setPanier(panierDto);
			
			userDto.setAdresse(user.getAdresse());
			userDto.setEntreprise(user.getEntreprise());


			RoleDto roleDto = new RoleDto();
			if (user.getRole()!=null) {
				roleDto.setId(user.getRole().getId());
				roleDto.setLabel(user.getRole().getLabel());
			}
			userDto.setRole(roleDto);

//			userDto = this.modelMapper.map(me, UserDto.class);
//			// solution temporaire
//			// ne pas remonter les mots de passe pour le service get
//			userDto.setPassword(null);
//			userDto.setTokenSecret(null);
//			userDto.setRole(this.modelMapper.map(me.getRole(), RoleDto.class));
			
			return Optional.of(userDto);
		
		}
		return Optional.empty();
	}

	@Override
	public UserDto findById(Integer userId) {
		Optional<User> userOpt = this.userRepository.findById(userId);
		UserDto userDto = new UserDto();
		if (userOpt.isPresent()) {
			User user = userOpt.get();
		
			userDto.setId(user.getId());
			userDto.setNom(user.getNom());
			userDto.setPrenom(user.getPrenom());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
			userDto.setNumClient(user.getNumClient());
			
			userDto.setAdresse(user.getAdresse());
			userDto.setEntreprise(user.getEntreprise());

			PanierDto panierDto = new PanierDto();
			if (user.getPanier()!=null) {
				panierDto.setId(user.getPanier().getId());
				panierDto.setDateValidation(user.getPanier().getDateValidation());
				panierDto.setTotal(user.getPanier().getTotal());
			}
			userDto.setPanier(panierDto);

			RoleDto roleDto = new RoleDto();
			if (user.getRole()!=null) {
				roleDto.setId(user.getRole().getId());
				roleDto.setLabel(user.getRole().getLabel());
			}
			userDto.setRole(roleDto);
	}
		return userDto;
	}


	@Override
	public boolean update(UserDto user, int id) {
		Optional<User> userOp = this.userRepository.findById(id);
		if (userOp.isPresent()) {
			User userE = userOp.get();

			userE.setEmail(user.getEmail());
			userE.setAdresse(user.getAdresse());
			userE.setEntreprise(user.getEntreprise());

			this.userRepository.save(userE);

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

