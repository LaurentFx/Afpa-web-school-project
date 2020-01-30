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
	private UserRepository invitationService;

	@Autowired
	private ManifestationRepository  manifestationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> findAll() {
		return this.invitationService.findAll()
				.stream()
				.map(v-> { 
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
				})
				.collect(Collectors.toList());
	}

	//    @Override
	//    public List <UserDto> findByRole (int id) {
	//    	return this.invitationService.findByRoleId(id);
	//    	
	//    }

	@Override
	public List<UserDto> findAllVipsByManifestation (int id) {
		Optional <Manifestation> manifOp = this.manifestationRepository.findById(id);
		List <UserDto> ListVipsByManifestation = new ArrayList<UserDto>();

		if (manifOp.isPresent()) {
			ManifestationDto manifestationDto = modelMapper.map(manifOp.get(),ManifestationDto.class);
			for (UserDto m : manifestationDto.getListVips()) {
				ListVipsByManifestation.add(m);
			}
		}
		return ListVipsByManifestation;
	}


	@Override
	public List<UserDto> findByRole(int id) {
		List <User> listUsers =  this.invitationService.findAll();

		List <UserDto> listByRole = new ArrayList<UserDto> ();

		for (User user : listUsers) {
			if (user.getRole().getId()==5) { 
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
		User user = this.invitationService.save
				(this.modelMapper.map(userDto, User.class));
		userDto.setId(user.getId());
		return userDto;
	}


	@Override
	public UserDto findById(int id) {
		Optional<User> userOp = this.invitationService.findById(id);
		UserDto userDto = null;
		if (userOp.isPresent()) {
			User user = userOp.get();
			userDto = this.modelMapper.map(user, UserDto.class);
		}
		return userDto;
	}


	@Override
	public boolean update(ManifestationDto manifestationDto, int id) {
		Optional<User> vipOp = this.invitationService.findById(id);
		
		if (vipOp.isPresent()) {
			Optional <Manifestation> manifestationOp= this.manifestationRepository.findById(manifestationDto.getId());
			Manifestation manifestation = new Manifestation();
			
			List <User> list = new ArrayList<User>();
			if (manifestationOp.isPresent()) {
				manifestation = manifestationOp.get();
				list.add(vipOp.get());
				manifestation.setListVips(list);
				}
			
			this.manifestationRepository.save(manifestation);
			//this.invitationService.save(this.modelMapper.map(user,User.class));
			return true;
		}
		return false;
	}


	@Override
	public boolean delete(int id) {
		if (this.invitationService.existsById(id)) {
			this.invitationService.deleteById(id);
			System.err.println("user supprimé");
			return true;
		}
		return false;
	}


}