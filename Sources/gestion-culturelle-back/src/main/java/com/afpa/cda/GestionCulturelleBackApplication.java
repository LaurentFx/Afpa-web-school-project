package com.afpa.cda;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.afpa.cda.constant.AdminUserDefaultConf;
import com.afpa.cda.dao.PersonneRepository;
import com.afpa.cda.dao.RoleRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.entity.Personne;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.TypeSalle;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GestionCulturelleBackApplication  implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GestionCulturelleBackApplication.class, args);
	}

	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH");
	}
	
	@Bean
	public CommandLineRunner init(RoleRepository roleRepository, PersonneRepository userRepository,AdminUserDefaultConf adminUserConf) {
		return (String... args)->{
			
			Role resp = new Role (1,"RESP");
			Role admin = new Role (2,"ADMIN");
			Role anim  = new Role (3,"ANIM");
			Role client = new Role (4,"CLIENT");
			Role vip = new Role (5,"VIP");
			
			initRole(roleRepository,resp);
			initRole(roleRepository,admin);
			initRole(roleRepository,anim);
			initRole(roleRepository,client);
			initRole(roleRepository,vip);
			
			// ne pas oublier de bloquer la création d utilisateur avec le nom ou prenom admin
			Optional<Personne> adminE = userRepository.findByNom(adminUserConf.getNom());
			if(! adminE.isPresent()) {
				userRepository.save(Personne.builder()
				.nom(adminUserConf.getNom())
				.prenom(adminUserConf.getPrenom())
				.password(adminUserConf.getPassword())
				.role(roleRepository.findById(resp.getId()).get())
				.build());
			}
		};
	}
    
	private void initRole(RoleRepository roleRepository, Role role) {
		Optional<Role> roleBddOpt = roleRepository.findById(role.getId());
		if( roleBddOpt.isPresent() ) {
			Role roleBdd = roleBddOpt.get();
			if(! roleBdd.getLabel().equals(role.getLabel())) {
				throw new RuntimeException("\n--- > > >  un autre role "+roleBdd.getLabel()+" a le code "+role.getId()+" résérvé pour "+role.getLabel());
			}
		} else {
			roleRepository.save(
					Role.builder()
					.id(role.getId())
					.label(role.getLabel())
					.build());
		}
	}
    
    
}