package com.afpa.cda.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dto.ManagerDto;
import com.afpa.cda.entity.Personne;
import com.afpa.cda.entity.Role;


@Service
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	private PersonneRepository managerRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public List<ManagerDto> findAll() {
		return this.managerRepository.findAll()
				.stream()
				.map(a-> ManagerDto.builder()
						.id(a.getId())
						.nom(a.getNom())
						.prenom(a.getPrenom())
						.salaire(a.getSalaire())

						.role(RoleDto.builder()
								.id(c.getRoles().getId()
										.label(c.getRoles().getLabel())
										.build()
										.build())
								.collect(Collectors.toList());		
	}	


	@Override
	public ManagerDto add(ManagerDto manager) {
		try {
			Personne manag = this.managerRepository.save(this.modelMapper.map(artiste,Personne.class));
			manager.setId(manag.getId());
			manager.setNom(manag.getNom());
			manager.setPrenom(manag.getPrenom());
			System.err.println("manager ajouté");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		return manager;
	}


	@Override
	public boolean updateManager(ManagerDto manag, int id) {
		Optional <Personne> managerOp= this.managerRepository.findById(id);
		if(managerOp.isPresent()) {
			Personne manager = managerOp.get();
			manager.setNom(manag.getNom());
			manager.setPrenom(manag.getPrenom());		
			manager.setRoles(Role.builder().id(manager.getId().build());
			this.managerRepository.save(manager);
			System.err.println("artiste mise à jour");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteManager(int id) {
		if(this.managerRepository.existsById(id)) {
			this.managerRepository.deleteById(id);
			System.err.println("manager supprimé");
			return true;
		}
		return false;
	}

	@Override
	public ManagerDto findById(int id) {
		Optional<Personne> managerOp = this.managerRepository.findById(id);
		ManagerDto manager=null;
		if(managerOp.isPresent()) {
			Personne manag = managerOp.get();
			manager = this.modelMapper.map(manag,ManagerDto.class);
		}
		return manager;
	}

}
