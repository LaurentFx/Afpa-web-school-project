package com.afpa.cda.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.InvitationRepository;
import com.afpa.cda.dao.ReservationRepository;
import com.afpa.cda.dao.RoleRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.InvitationDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Invitation;
import com.afpa.cda.entity.Reservation;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IInvitationService;
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
	private AnimationRepository animationRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private IInvitationService invitationService;

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

			userDto.setAdresse(user.getAdresse());
			userDto.setEntreprise(user.getEntreprise());

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
	public List<UserDto> findByRole(int id) {
		return this.userRepository.findUserByRole(id).stream().map(user -> {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setNom(user.getNom());
			userDto.setPrenom(user.getPrenom());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(null);
			userDto.setTokenSecret(null);
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
	public List<UserDto> findVipsToInvite(int id) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List <UserDto> listVips = findByRole(5);
		List <InvitationDto> listInvitation= invitationService.findInvitationByManifestationId(id);
		List <UserDto> listVipsVipsToInvite = new ArrayList <UserDto> (listVips);

		for (UserDto userDto : listVips) {
			for (InvitationDto invitationDto : listInvitation) {
				if (userDto.getId()==invitationDto.getVip().getId()) {
					listVipsVipsToInvite.remove(userDto);
				}
			}
		}
		
		return listVipsVipsToInvite;
	}

	@Override
	public int add(UserDto userDto) {
		int id=0;
		Optional <User> userOp = this.userRepository.findUserByNomAndPrenom(userDto.getNom(), userDto.getPrenom());
		if (!userOp.isPresent()) {
			id=this.userRepository.save(this.modelMapper.map(userDto, User.class)).getId();
			return id;
		}

		return id;
	}

	@Override
	public boolean addClient(UserDto userDto) {
		Optional <User> userOp = this.userRepository.findUserByNomAndPrenom(userDto.getNom(), userDto.getPrenom());
		if (!userOp.isPresent()) {
			User user = this.modelMapper.map(userDto,User.class);
			Optional<Role> roleOp=roleRepository.findById(4);
			if (roleOp.isPresent()) {
				user.setRole(roleOp.get());
			}

			this.userRepository.save(user);
			userDto = modelMapper.map(user, UserDto.class);
			userDto.setId(user.getId());

			return false;
		}
		return true;
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
	public boolean update(UserDto userDto, int id) {
		Optional<User> userOp = this.userRepository.findById(id);
		if (userOp.isPresent()) {
			User user = userOp.get();

			user.setEmail(userDto.getEmail());
			user.setAdresse(userDto.getAdresse());
			user.setEntreprise(userDto.getEntreprise());
			this.userRepository.save(user);

			return true;
		}

		return false;
	}

	@Override
	public boolean delete(int id) {
		Optional<User> userOp = this.userRepository.findById(id);
		if (userOp.isPresent()) {
			User user = userOp.get();

			if (user.getRole().getId()==3) {
				List <Animation> listAnimations = this.animationRepository.findAnimationByUser(id);
				if (!listAnimations.isEmpty()) {
					return false;
				}
			}
			if (user.getRole().getId()==4) {
				List <Reservation> listReservations = this.reservationRepository.findReservationByUser(id);
				if (!listReservations.isEmpty()) {
					return false;
				}
			}
			if (user.getRole().getId()==5) {
				List <Invitation> listInvitations = this.invitationRepository.findInvitationByUser(id);
				if (!listInvitations.isEmpty()) {
					return false;
				}
			}
		}
		if (this.userRepository.existsById(id)) {
			this.userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

