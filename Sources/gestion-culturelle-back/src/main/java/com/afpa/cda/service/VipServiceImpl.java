package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.VipDto;
import com.afpa.cda.entity.User;
@Service
public class VipServiceImpl implements IVipService {
	
    @Autowired
    private UserRepository vipRepository;
    
    @Autowired
    private ModelMapper modelMapper;
	
    
    
    @Override
	public List<VipDto> findAll() {
		return this.vipRepository.findAll()
				.stream()
				.map(v-> { 
					VipDto vipDto = new VipDto();
					vipDto.setId(v.getId());
					vipDto.setNom(v.getNom());
					vipDto.setPrenom(v.getPrenom());
					vipDto.setEmail(v.getEmail());
					vipDto.setPassword(v.getPassword());
					vipDto.setAdresse(v.getAdresse());
					
					RoleDto rolDto = new RoleDto();
					rolDto.setId(v.getRole().getId());
					rolDto.setLabel(v.getRole().getLabel());
					vipDto.setRole(rolDto);
					
					vipDto.setEntreprise(v.getEntreprise());
					return vipDto;
				})
				.collect(Collectors.toList());
	}
	
    
    @Override
	public VipDto add(VipDto vip) {
		User perE = this.vipRepository.save
				(this.modelMapper.map(vip, User.class));
		vip.setId(perE.getId());
		return vip;
	}
	
	
    @Override
	public VipDto findById(int id) {
		Optional<User> perE = this.vipRepository.findById(id);
		VipDto vipDto = null;
		if (perE.isPresent()) {
			User pr = perE.get();
			vipDto = this.modelMapper.map(pr, VipDto.class);
		}
		return vipDto;
	}
	
    
    @Override
	public boolean updateVip(VipDto vip, int id) {
    	Optional<User> perE = this.vipRepository.findById(id);
    	if (perE.isPresent()) {
			this.vipRepository.save(this.modelMapper.map(vip,User.class));
		return true;
    	}
		return false;
	}
	
    
    @Override
	public boolean deleteVop(int id) {
		if (this.vipRepository.existsById(id)) {
			this.vipRepository.deleteById(id);
			System.err.println("vip supprimée");
			return true;
		}
		return false;
	}

    
}