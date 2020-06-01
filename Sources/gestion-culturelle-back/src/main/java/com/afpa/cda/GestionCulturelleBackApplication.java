package com.afpa.cda;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.afpa.cda.constant.AdminUserDefaultConf;
import com.afpa.cda.dao.AnimationRepository;
import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.RoleRepository;
import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.entity.Animation;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Role;
import com.afpa.cda.entity.Salle;
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
	public CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository,
			AdminUserDefaultConf adminUserConf, TypeSalleRepository typeSalleRepository,
			SalleRepository salleRepository, AnimationRepository animationRepository,
			ManifestationRepository manifestationRepository) {
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

			Salle zenith = new Salle ();
			zenith.setId(1);
			zenith.setLabel("Zenith");
			zenith.setCapacite(800);
			zenith.setFraisjournalier(300);
			zenith.setPlacesVip(4);
			zenith.setTypesalle(concert);

			Salle foot = new Salle ();
			foot.setId(2);
			foot.setLabel("Terrain");
			foot.setCapacite(1200);
			foot.setFraisjournalier(200);
			foot.setPlacesVip(6);
			foot.setTypesalle(stade);

			Salle colisee = new Salle ();
			colisee.setId(3);
			colisee.setLabel("Colisée");
			colisee.setCapacite(400);
			colisee.setFraisjournalier(400);
			colisee.setPlacesVip(2);
			colisee.setTypesalle(theatre);

			initSalle(salleRepository, typeSalleRepository,zenith);
			initSalle(salleRepository, typeSalleRepository, foot);
			initSalle(salleRepository, typeSalleRepository,colisee);

			String adresse = "Lille";
			String mail = "cda@afpa.com";
			String entreprise="Afpa";
			String password = "1234";

			User resp2 = new User ();
			resp2.setId(1);
			resp2.setNom("resp2");
			resp2.setPrenom("resp2");
			resp2.setPassword(password);
			resp2.setAdresse(adresse);
			resp2.setEmail(mail);
			resp2.setEntreprise(entreprise);
			resp2.setRole(resp);
			
			User admin1 = new User ();
			admin1.setId(2);
			admin1.setNom("admin1");
			admin1.setPrenom("admin1");
			admin1.setPassword(password);
			admin1.setAdresse(adresse);
			admin1.setEmail(mail);
			admin1.setEntreprise(entreprise);
			admin1.setRole(admin);
			
			User admin2 = new User ();
			admin2.setId(3);
			admin2.setNom("admin2");
			admin2.setPrenom("admin2");
			admin2.setPassword(password);
			admin2.setAdresse(adresse);
			admin2.setEmail(mail);
			admin2.setEntreprise(entreprise);
			admin2.setRole(admin);

			User anim1 = new User ();
			anim1.setId(4);
			anim1.setNom("anim1");
			anim1.setPrenom("anim1");
			anim1.setPassword(password);
			anim1.setAdresse(adresse);
			anim1.setEmail(mail);
			anim1.setEntreprise(entreprise);
			anim1.setRole(anim);

			User anim2 = new User ();
			anim2.setId(5);
			anim2.setNom("anim2");
			anim2.setPrenom("anim2");
			anim2.setPassword(password);
			anim2.setAdresse(adresse);
			anim2.setEmail(mail);
			anim2.setEntreprise(entreprise);
			anim2.setRole(anim);

			User anim3 = new User ();
			anim3.setId(6);
			anim3.setNom("anim3");
			anim3.setPrenom("anim3");
			anim3.setPassword(password);
			anim3.setAdresse(adresse);
			anim3.setEmail(mail);
			anim3.setEntreprise(entreprise);
			anim3.setRole(anim);

			User vip1 = new User ();
			vip1.setId(7);
			vip1.setNom("vip1");
			vip1.setPrenom("vip1");
			vip1.setPassword(password);
			vip1.setAdresse(adresse);
			vip1.setEmail(mail);
			vip1.setEntreprise(entreprise);
			vip1.setRole(vip);

			User vip2 = new User ();
			vip2.setId(8);
			vip2.setNom("vip2");
			vip2.setPrenom("vip2");
			vip2.setPassword(password);
			vip2.setAdresse(adresse);
			vip2.setEmail(mail);
			vip2.setEntreprise(entreprise);
			vip2.setRole(vip);

			User vip3 = new User ();
			vip3.setId(9);
			vip3.setNom("vip3");
			vip3.setPrenom("vip3");
			vip3.setPassword(password);
			vip3.setAdresse(adresse);
			vip3.setEmail(mail);
			vip3.setEntreprise(entreprise);
			vip3.setRole(vip);

			User vip4 = new User ();
			vip4.setId(10);
			vip4.setNom("vip4");
			vip4.setPrenom("vip4");
			vip4.setPassword(password);
			vip4.setAdresse(adresse);
			vip4.setEmail(mail);
			vip4.setEntreprise(entreprise);
			vip4.setRole(vip);

			User vip5 = new User ();
			vip5.setId(11);
			vip5.setNom("vip5");
			vip5.setPrenom("vip5");
			vip5.setPassword(password);
			vip5.setAdresse(adresse);
			vip5.setEmail(mail);
			vip5.setEntreprise(entreprise);
			vip5.setRole(vip);

			User vip6 = new User ();
			vip6.setId(12);
			vip6.setNom("vip6");
			vip6.setPrenom("vip6");
			vip6.setPassword(password);
			vip6.setAdresse(adresse);
			vip6.setEmail(mail);
			vip6.setEntreprise(entreprise);
			vip6.setRole(vip);
			
			initUser(userRepository,resp2);
			initUser(userRepository,admin1);
			initUser(userRepository,admin2);
			initUser(userRepository,anim1);
			initUser(userRepository,anim2);
			initUser(userRepository,anim3);
			initUser(userRepository,vip1);
			initUser(userRepository,vip2);
			initUser(userRepository,vip3);
			initUser(userRepository,vip4);
			initUser(userRepository,vip5);
			initUser(userRepository,vip6);

			Animation animat1 = new Animation ();
			animat1.setId(1);
			animat1.setLabel("Match de football");
			animat1.setType("Sport");
			animat1.setPrix(7800);
			animat1.setNbreSpectateursPrevus(1000);
			animat1.setAnimateur(anim1);

			Animation animat2 = new Animation ();
			animat2.setId(2);
			animat2.setLabel("Concert Rock");
			animat2.setType("Musique");
			animat2.setPrix(7500);
			animat2.setNbreSpectateursPrevus(650);
			animat2.setAnimateur(anim2);

			Animation animat3 = new Animation ();
			animat3.setId(3);
			animat3.setLabel("Pièce classique");
			animat3.setType("Art");
			animat3.setPrix(1520);
			animat3.setNbreSpectateursPrevus(300);
			animat3.setAnimateur(anim3);
			
			Animation animat4 = new Animation ();
			animat4.setId(4);
			animat4.setLabel("Match de basket");
			animat4.setType("Sport");
			animat4.setPrix(3900);
			animat4.setNbreSpectateursPrevus(800);
			animat4.setAnimateur(anim1);

			Animation animat5 = new Animation ();
			animat5.setId(5);
			animat5.setLabel("Soirée Techno");
			animat5.setType("Musique");
			animat5.setPrix(3600);
			animat5.setNbreSpectateursPrevus(900);
			animat5.setAnimateur(anim2);

			Animation animat6 = new Animation ();
			animat6.setId(6);
			animat6.setLabel("Opéra");
			animat6.setType("Art");
			animat6.setPrix(800);
			animat6.setNbreSpectateursPrevus(200);
			animat6.setAnimateur(anim3);

			initAnimation(animationRepository,userRepository,animat1);
			initAnimation(animationRepository,userRepository,animat2);
			initAnimation(animationRepository,userRepository,animat3);
			initAnimation(animationRepository,userRepository,animat4);
			initAnimation(animationRepository,userRepository,animat5);
			initAnimation(animationRepository,userRepository,animat6);
			
			Manifestation manif1 = new Manifestation ();
			manif1.setId(1);
			manif1.setLabel("Lille-Valenciennes");
			manif1.setDateValidation(new Date());
			manif1.setValidateur(resp2);
			manif1.setAnimation(animat1);
			manif1.setDateDebut(new Date());
			manif1.setDateFin(new Date());
			manif1.setCout(8000);
			manif1.setSalle(foot);
			manif1.setPrixBillet(10);
			manif1.setReservations(996);
			manif1.setReservationsVip(4);
			
			Manifestation manif2 = new Manifestation ();
			manif2.setId(2);
			manif2.setLabel("U2 - 2020");
			manif2.setDateValidation(new Date());
			manif2.setValidateur(resp2);
			manif2.setAnimation(animat2);
			manif2.setDateDebut(new Date());
			manif2.setDateFin(new Date());
			manif2.setCout(6000);
			manif2.setSalle(zenith);
			manif2.setPrixBillet(8);
			manif2.setReservations(646);
			manif2.setReservationsVip(4);
			
			Manifestation manif3 = new Manifestation ();
			manif3.setId(3);
			manif3.setLabel("Moliere");
			manif3.setDateValidation(new Date());
			manif3.setValidateur(resp2);
			manif3.setAnimation(animat3);
			manif3.setDateDebut(new Date());
			manif3.setDateFin(new Date());
			manif3.setCout(3000);
			manif3.setSalle(colisee);
			manif3.setPrixBillet(6);
			manif3.setReservations(296);
			manif3.setReservationsVip(4);
			
			initManifestation(manifestationRepository, animationRepository, userRepository, salleRepository, manif1);
			initManifestation(manifestationRepository, animationRepository, userRepository, salleRepository, manif2);
			initManifestation(manifestationRepository, animationRepository, userRepository, salleRepository, manif3);

			// ne pas oublier de bloquer la création d'utilisateur avec le nom ou prenom admin
			Optional<User> resp1 = userRepository.findUserByNom(adminUserConf.getNom());
			if(! resp1.isPresent()) {
				userRepository.save(User.builder()
						.nom(adminUserConf.getNom())
						.prenom(adminUserConf.getPrenom())
						.password(adminUserConf.getPassword())
						.adresse(adresse)
						.email(mail)
						.entreprise(entreprise)

						// H2
						//	.role(roleRepository.findById(resp.getId()).get())

						// Postgres
						.role(resp)
						.build());
			}
		};

	}


	private void initRole(RoleRepository roleRepository, Role role) {
		Optional<Role> roleBddOpt = roleRepository.findRoleByLabel(role.getLabel());
		if( ! roleBddOpt.isPresent() ) {

			// H2
			//			Role roleBdd = roleBddOpt.get();  
			//	if(! roleBdd.getLabel().equals(role.getLabel())) {
			//			throw new RuntimeException("\n--- > > >  un autre role "+roleBdd.getLabel()+" a l'id "+role.getId()+" résérvé pour "+role.getLabel());
			//		}
			//		} else {
			//			roleRepository.save(

			// Postgres
			role = roleRepository.save(
					Role.builder()
					.id(role.getId())
					.label(role.getLabel())
					.build());
		} 
	}

	private void initTypeSalle(TypeSalleRepository typeSalleRepository, TypeSalle typeSalle) {
		Optional<TypeSalle> typeSalleBddOpt = typeSalleRepository.findTypeSalleByLabel(typeSalle.getLabel());
		if( ! typeSalleBddOpt.isPresent() ) {

			// H2
			//			TypeSalle typeSalleBdd = typeSalleBddOpt.get();
			//			if(! typeSalleBdd.getLabel().equals(typeSalle.getLabel())) {
			//				throw new RuntimeException("\n--- > > >  un autre type de salle "+typeSalleBdd.getLabel()+" a l'id "+typeSalle.getId()+" résérvé pour "+typeSalle.getLabel());
			//			}
			//		} else {
			//typeSalleRepository.save(

			// Postgres
			typeSalle = typeSalleRepository.save(

					TypeSalle.builder()
					.id(typeSalle.getId())
					.label(typeSalle.getLabel())
					.build());
		}
	}

	private void initSalle(SalleRepository salleRepository, TypeSalleRepository typeSalleRepository,Salle salle) {
		Optional<Salle> salleBddOpt = salleRepository.findSalleByLabel(salle.getLabel());
		if( ! salleBddOpt.isPresent() ) {

			salle = salleRepository.save(
					Salle.builder()
					.id(salle.getId())
					.label(salle.getLabel())
					.capacite(salle.getCapacite())
					.fraisjournalier(salle.getFraisjournalier())
					.placesVip(salle.getPlacesVip())
					.typesalle(typeSalleRepository.findTypeSalleByLabel(salle.getTypesalle().getLabel()).get())
					.build());

		}
	}

	private void initUser(UserRepository userRepository, User user) {
		Optional<User> userNomBddOpt = userRepository.findUserByNom(user.getNom());
		Optional<User> userPrenomBddOpt = userRepository.findUserByPrenom(user.getPrenom());
		if( ! userNomBddOpt.isPresent() &&  ! userPrenomBddOpt.isPresent()) {

			user = userRepository.save(
					User.builder()
					.id(user.getId())
					.nom(user.getNom())
					.prenom(user.getPrenom())
					.password(user.getPassword())
					.adresse(user.getAdresse())
					.email(user.getEmail())
					.entreprise(user.getEntreprise())
					.role(user.getRole())
					.build());

		}
	}

	private void initAnimation (AnimationRepository animationRepository, UserRepository userRepository, Animation animation) {
		Optional<Animation> animationBddOpt = animationRepository.findAnimationByLabel(animation.getLabel());
		if( ! animationBddOpt.isPresent() ) {

			animation = animationRepository.save(
					Animation.builder()
					.id(animation.getId())
					.label(animation.getLabel())
					.type(animation.getType())
					.prix(animation.getPrix())
					.nbreSpectateursPrevus(animation.getNbreSpectateursPrevus())
					.animateur(userRepository.findUserByNom(animation.getAnimateur().getNom()).get())
					.build());
		}
	}
	
	private void initManifestation (ManifestationRepository manifestationRepository, AnimationRepository animationRepository,
			UserRepository userRepository, SalleRepository salleRepository, Manifestation manifestation) {
		Optional<Manifestation> manifestationBddOpt = manifestationRepository.findManifestationByLabel(manifestation.getLabel());
		if( ! manifestationBddOpt.isPresent() ) {

			manifestation = manifestationRepository.save(
					Manifestation.builder()
					.id(manifestation.getId())
					.label(manifestation.getLabel())
					.dateValidation(manifestation.getDateValidation())
					.validateur(userRepository.findUserByNom(manifestation.getValidateur().getNom()).get())
					.animation(animationRepository.findAnimationByLabel(manifestation.getAnimation().getLabel()).get())
					.dateDebut(manifestation.getDateDebut())
					.dateFin(manifestation.getDateFin())
					.cout(manifestation.getCout())
					.salle(salleRepository.findSalleByLabel(manifestation.getSalle().getLabel()).get())
					.prixBillet(manifestation.getPrixBillet())
					.reservations(manifestation.getReservations())
					.reservationsVip(manifestation.getReservationsVip())
					.build());
		}
	}
	
	
	
}




