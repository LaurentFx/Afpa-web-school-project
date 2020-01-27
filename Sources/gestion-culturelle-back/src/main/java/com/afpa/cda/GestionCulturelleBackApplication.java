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
import com.afpa.cda.dao.PanierRepository;
import com.afpa.cda.dao.RoleRepository;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.entity.Panier;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.TypeSalle;
import com.afpa.cda.entity.User;

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
	public CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository,AdminUserDefaultConf adminUserConf, TypeSalleRepository typeSalleRepository, PanierRepository panierRepository) {
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

			TypeSalle concert = new TypeSalle (1,"Concert");
			TypeSalle stade = new TypeSalle (2,"Stade");
			TypeSalle theatre = new TypeSalle (3,"Theatre");

			initTypeSalle(typeSalleRepository,concert);
			initTypeSalle(typeSalleRepository,stade);
			initTypeSalle(typeSalleRepository,theatre);

			String adresse = "Lille";
			String mail = "cda@afpa.com";
			String entreprise="Afpa";

			// ne pas oublier de bloquer la création d'utilisateur avec le nom ou prenom admin
			Optional<User> adminE = userRepository.findByNom(adminUserConf.getNom());
			if(! adminE.isPresent()) {
				userRepository.save(User.builder()
						.nom(adminUserConf.getNom())
						.prenom(adminUserConf.getPrenom())
						.password(adminUserConf.getPassword())
						.adresse(adresse)
						.email(mail)
						.entreprise(entreprise)
						.role(roleRepository.findById(resp.getId()).get())
						//				.role(resp)
						.build());
			}
		};

	}


	private void initRole(RoleRepository roleRepository, Role role) {
		Optional<Role> roleBddOpt = roleRepository.findByLabel(role.getLabel());
		if( ! roleBddOpt.isPresent() ) {
//			Role roleBdd = roleBddOpt.get();
//			if(! roleBdd.getLabel().equals(role.getLabel())) {
//				throw new RuntimeException("\n--- > > >  un autre role "+roleBdd.getLabel()+" a l'id "+role.getId()+" résérvé pour "+role.getLabel());
//			}
//		} else {

			//			role = roleRepository.save(
			roleRepository.save(
					Role.builder()
					.id(role.getId())
					.label(role.getLabel())
					.build());
		} 
	}

	private void initTypeSalle(TypeSalleRepository typeSalleRepository, TypeSalle typeSalle) {
		Optional<TypeSalle> typeSalleBddOpt = typeSalleRepository.findById(typeSalle.getId());
		if( ! typeSalleBddOpt.isPresent() ) {
//			TypeSalle typeSalleBdd = typeSalleBddOpt.get();
//			if(! typeSalleBdd.getLabel().equals(typeSalle.getLabel())) {
//				throw new RuntimeException("\n--- > > >  un autre type de salle "+typeSalleBdd.getLabel()+" a l'id "+typeSalle.getId()+" résérvé pour "+typeSalle.getLabel());
//			}
//		} else {
			//			typeSalle = typeSalleRepository.save(
			typeSalleRepository.save(
					TypeSalle.builder()
					.id(typeSalle.getId())
					.label(typeSalle.getLabel())
					.build());
		}
	}

}




