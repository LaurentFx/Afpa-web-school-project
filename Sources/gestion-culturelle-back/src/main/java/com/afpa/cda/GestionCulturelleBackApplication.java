package com.afpa.cda;

import java.util.Date;
import java.util.GregorianCalendar;
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

	//	@SuppressWarnings("deprecation")
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

			Salle zenith = new Salle(1,"Zenith",800,4,300,concert);
			Salle foot = new Salle (2,"Terrain",1200,6,200,stade);
			Salle colisee = new Salle (3,"Colisée",400,2,400,theatre);
			initSalle(salleRepository, typeSalleRepository,zenith);
			initSalle(salleRepository, typeSalleRepository, foot);
			initSalle(salleRepository, typeSalleRepository,colisee);

			String adresse = "Lille";
			String mail = "cda@afpa.com";
			String entreprise="Afpa";
			String password = "1234";
			User resp2 = new User(1,"resp2","resp2",password,mail,null,resp,adresse,null,null,entreprise);
			User admin1 = new User (2,"admin1","admin1",password,mail,null,admin,adresse,null,null,entreprise);
			User admin2 = new User (3,"admin2","admin2",password,mail,null,admin,adresse,null,null,entreprise);
			User anim1 = new User (4,"anim1","anim1",password,mail,null,anim,adresse,null,null,entreprise);
			User anim2 = new User (5,"anim2","anim2",password,mail,null,anim,adresse,null,null,entreprise);
			User anim3 = new User (6,"anim3","anim3",password,mail,null,anim,adresse,null,null,entreprise);
			User vip1 = new User (6,"vip1","vip1",password,mail,null,vip,adresse,null,null,entreprise);
			User vip2 = new User (6,"vip2","vip2",password,mail,null,vip,adresse,null,null,entreprise);
			User vip3 = new User (6,"vip3","vip3",password,mail,null,vip,adresse,null,null,entreprise);
			User vip4 = new User (6,"vip4","vip4",password,mail,null,vip,adresse,null,null,entreprise);
			User vip5 = new User (6,"vip5","vip5",password,mail,null,vip,adresse,null,null,entreprise);
			User vip6 = new User (6,"vip6","vip6",password,mail,null,vip,adresse,null,null,entreprise);
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

			Animation animat1 = new Animation (1,"Match de football","sport",7800,1000,anim1);
			Animation animat2 = new Animation (2,"Concert Rock","Musique",7500,650,anim2);
			Animation animat3 = new Animation (3,"Pièce classique","Art",1520,300,anim3);
			Animation animat4 = new Animation (4,"Match de basket","Sport",3900,800,anim1);
			Animation animat5 = new Animation (5,"Soirée Techno","Musique",3600,900,anim2);
			Animation animat6 = new Animation (6,"Opéra","Art",800,200,anim3);
			initAnimation(animationRepository,userRepository,animat1);
			initAnimation(animationRepository,userRepository,animat2);
			initAnimation(animationRepository,userRepository,animat3);
			initAnimation(animationRepository,userRepository,animat4);
			initAnimation(animationRepository,userRepository,animat5);
			initAnimation(animationRepository,userRepository,animat6);

			Date date1Juin = new GregorianCalendar(2020,5,1).getTime();
			Date date7Juin = new GregorianCalendar(2020,5,7).getTime(); 
			Date date13Juin = new GregorianCalendar(2020,5,13).getTime();
			Date date14Juin = new GregorianCalendar(2020,5,14).getTime();
			Date date26Juin =  new GregorianCalendar(2020,5,26).getTime();
			Date date28Juin =  new GregorianCalendar(2020,5,28).getTime();

			Manifestation manif1 = new Manifestation(1,"Lille-Valenciennes",date1Juin,resp2,animat1,date7Juin,date7Juin,8000,foot,10,996,4);
			Manifestation manif2 = new Manifestation (2,"U2 - 2020",date1Juin,resp2,animat2,date13Juin,date14Juin,6000,zenith,8,646,4);
			Manifestation manif3 = new Manifestation (3,"Moliere",date1Juin,resp2,animat3,date26Juin,date28Juin,3000,colisee,6,296,4);
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




