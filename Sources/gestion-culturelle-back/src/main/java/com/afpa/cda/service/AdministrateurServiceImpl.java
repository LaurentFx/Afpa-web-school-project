package com.afpa.cda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.AdminDto;
import com.afpa.cda.entity.Personne;

@Service
public class AdministrateurServiceImpl implements IAdminService {

	@Autowired
	private PersonneRepository personneRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<AdminDto> findAll() {
		
		return this.personneRepository.findAll()
		.stream()
		.map(ts-> this.modelMapper.map(ts,AdminDto.class ))
		.collect(Collectors.toList());
	}

	@Override
	public AdminDto add(AdminDto admin) {
		
		Personne personne = this.personneRepository.save(this.modelMapper.map(admin,Personne.class));
		admin.setNom(personne.getNom());
		admin.setPrenom(personne.getPrenom());
		admin.setQualification(personne.getQualification());
		System.err.println("administrateur ajouté");
		
		return admin;
	}

	@Override
	public boolean updateAdmin(AdminDto admin, int id) {
	
		Optional<Personne> adminOp = this.personneRepository.findById(id);
		if(adminOp.isPresent()) {
			Personne personne = adminOp.get();
			personne.setNom(admin.getNom());
			personne.setQualification(admin.getQualification());
			System.err.println("administrateur mise à jour");
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteAdmin(int id) {
	
		if(this.personneRepository.existsById(id)) {
			this.personneRepository.deleteById(id);
			System.err.println("administrateur supprimé");
			return true;
		}
		return false;
	
	}

	@Override
	public AdminDto findById(int id) {
		Optional <Personne> manifOp = this.personneRepository.findById(id);
		AdminDto admin =null; 
		if(manifOp.isPresent()) {
			Personne personne= manifOp.get();

			admin=this.modelMapper.map(personne,AdminDto.class);
		}
		return admin;
	}

}