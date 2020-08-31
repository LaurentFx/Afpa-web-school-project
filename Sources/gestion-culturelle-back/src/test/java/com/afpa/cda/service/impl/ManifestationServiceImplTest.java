package com.afpa.cda.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.service.IAnimationService;
import com.afpa.cda.service.IManifestationService;
import com.afpa.cda.service.IRoleService;
import com.afpa.cda.service.ISalleService;
import com.afpa.cda.service.ITypeSalleService;
import com.afpa.cda.service.IUserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManifestationServiceImplTest {

	@Autowired
	IManifestationService manifestationService;

	@Autowired
	IRoleService roleService;

	@Autowired
	IUserService userService;

	@Autowired
	IAnimationService animationService;

	@Autowired
	ITypeSalleService typeSalleService;

	@Autowired
	ISalleService salleService;

	@Autowired
	ManifestationRepository manifestationRepository;

	//Création Objets test
	static RoleDto roleTest = new RoleDto (10,"ROLETEST");
	static UserDto userTest = new UserDto (10,"USERTEST","USERTEST","1234","cda@afpa.com", "Lille","10", roleTest,"Afpa"); 
	static TypeSalleDto typeSalleTest = new TypeSalleDto (10,"TYPESALLETEST");
	static SalleDto salleTest = new SalleDto (10,"SALLETEST",600,4,200,typeSalleTest);
	static AnimationDto animationTest = new AnimationDto (10,"ANIMATIONTEST","SPORT",1500,500,userTest);
	static Date dateValidation = new GregorianCalendar(2020,5,1).getTime();
	static Date dateDebut =  new GregorianCalendar(2020,5,15).getTime();
	static Date dateFin =  new GregorianCalendar(2020,5,19).getTime();
	static ManifestationDto manifestationDtoTest= new ManifestationDto(10,"MANIFESTATIONTEST",dateValidation,
			userTest,animationTest,dateDebut,dateFin,0,salleTest,0,0,0);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)  
	void testFindAll() {
		//Création liste nulle
		List <ManifestationDto> listManifestationsDto = null;
		assertNull(listManifestationsDto);

		// Liste de toutes les entités
		listManifestationsDto =	this.manifestationService.findAll();
		// Test si liste non nulle
		assertNotNull(listManifestationsDto);
		assertNotEquals(0,listManifestationsDto.size());
		assertTrue(listManifestationsDto.size()>0);

		System.out.println();
		System.out.println("Test this.manifestationService.FindAll() => "+listManifestationsDto);
		System.out.println();
	}

	@Test
	@Order(2)  
	void testAdd() {
		//Création entités test
		roleTest.setId(this.roleService.add(roleTest));
		typeSalleTest.setId(this.typeSalleService.add(typeSalleTest));
		userTest.setId(this.userService.add(userTest));
		salleTest.setId(this.salleService.add(salleTest));	
		animationTest.setId(this.animationService.add(animationTest));

		// Test de la liste entités avant ajout
		int size1 =  this.manifestationService.findAll().size();
		int id = 0;
		assertNotEquals(0,size1);
		assertEquals(0,id);

		// Ajout objet test
		id=this.manifestationService.add(manifestationDtoTest);
		manifestationDtoTest.setId(id);
		assertNotEquals (0,id);

		// Test après ajout si taille de la liste entités +1 
		int size2 = this.manifestationService.findAll().size();
		assertTrue(size2>size1);
		assertTrue(size2-1==size1);

		System.out.println("Add this.manifestationService.add(manifestationDtoTest) => "+manifestationDtoTest);
		System.out.println();
	}

	@Test
	@Order(3)  
	void testFindById() {
		//Création objet à tester
		ManifestationDto manifestationDto = null;
		assertNull(manifestationDto);

		// Recherche objet test
		manifestationDto = this.manifestationService.findById(manifestationDtoTest.getId());
		assertNotNull(manifestationDto);

		// Test si objet trouvé = objet test
		assertEquals(manifestationDto.getId(), manifestationDtoTest.getId());
		assertEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());
		System.out.println("Test this.manifestationService.findById(manifestationDtoTest.getId()) => "+manifestationDtoTest);
		System.out.println();
	}

	@Test
	@Order(4)  
	void testUpdate() {
		//Création objet modifié
		ManifestationDto manifestationDto = new ManifestationDto(10,"MANIFESTATIONTEST",dateValidation,
				userTest,animationTest,dateDebut,dateFin,0,salleTest,0,0,0);
		manifestationDto.setLabel("MANIFESATIONUPDATED");
		assertNotEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());

		boolean result = false;
		assertFalse(result);
		// Modification objet test
		result = this.manifestationService.update(manifestationDto,manifestationDtoTest.getId());
		assertTrue (result);
		manifestationDtoTest = this.manifestationService.findById(manifestationDtoTest.getId());

		// Test si objet test = objet modifié
		assertEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());

		System.out.println("Test this.manifestationService.update(manifestationDto,manifestationDtoTest.getId()) => "+manifestationDtoTest);
		System.out.println();
	}

	@Test
	@Order(6)  
	void testCalcul() {
		// Raz des données objet test
		manifestationDtoTest.setReservations(0);
		manifestationDtoTest.setReservationsVip(0);
		manifestationDtoTest.setCout(0);
		manifestationDtoTest.setPrixBillet(0);

		assertEquals(0,manifestationDtoTest.getReservations());
		assertEquals(0,manifestationDtoTest.getReservationsVip());
		assertEquals(0,manifestationDtoTest.getCout(),1);
		assertEquals(0,manifestationDtoTest.getPrixBillet(),1);

		// Calcul des données objet test
		manifestationDtoTest=this.manifestationService.calcul(manifestationDtoTest);

		// Test si données objet test !=0
		assertNotEquals(0,manifestationDtoTest.getReservations());
		assertNotEquals(0,manifestationDtoTest.getReservationsVip());
		assertNotEquals(0,manifestationDtoTest.getCout(),1);
		assertNotEquals(0,manifestationDtoTest.getPrixBillet(),1);

		System.out.println("Test this.manifestationService.calcul() => "+manifestationDtoTest);
		System.out.println();
	}

	@Test
	@Order(7)  
	void testFindAvailability() {
		boolean result=true;
		assertTrue(result);

		// Création dates test
		Date date1Juin = new GregorianCalendar(2020,5,1).getTime();
		Date date11Juin = new GregorianCalendar(2020,5,11).getTime(); 
		Date date13Juin = new GregorianCalendar(2020,5,13).getTime();
		Date date14Juin = new GregorianCalendar(2020,5,14).getTime();
		Date date15Juin =  new GregorianCalendar(2020,5,15).getTime();
		Date date19Juin =  new GregorianCalendar(2020,5,19).getTime();
		Date date20Juin =  new GregorianCalendar(2020,5,20).getTime();
		Date date21Juin =  new GregorianCalendar(2020,5,20).getTime();
		Date date25Juin =  new GregorianCalendar(2020,5,25).getTime();
		Date date27Juin =  new GregorianCalendar(2020,5,27).getTime();

		// Manifestation test du 15 au 19/06/2020
		manifestationDtoTest.setDateDebut(date15Juin);
		manifestationDtoTest.setDateFin(date19Juin);

		ManifestationDto manifestationDto = new ManifestationDto(10,"MANIFESTATIONTEST",date1Juin,
				userTest,animationTest,date15Juin,date19Juin,0,salleTest,0,0,0);

		// Manifestation à tester du 13 au 15/06/2020
		manifestationDto.setDateDebut(date13Juin);
		manifestationDto.setDateFin(date15Juin);

		// Resultat Test disponibilité = indisponible / false
		result=	manifestationService.findAvailability(manifestationDto);
		assertFalse(result);

		// Manifestation à tester du 19 au 21/06/2020
		manifestationDto.setDateDebut(date19Juin);
		manifestationDto.setDateFin(date21Juin);
		// Resultat Test disponibilité = indisponible / false
		result=	manifestationService.findAvailability(manifestationDto);
		assertFalse(result);

		// Manifestation à tester du 14 au 20/06/2020
		manifestationDto.setDateDebut(date14Juin);
		manifestationDto.setDateFin(date20Juin);
		// Resultat Test disponibilité = indisponible / false
		result=	manifestationService.findAvailability(manifestationDto);
		assertFalse(result);

		// Manifestation à tester du 11 au 13/06/2020
		manifestationDto.setDateDebut(date11Juin);
		manifestationDto.setDateFin(date13Juin);
		// Resultat Test disponibilité = disponible / true
		result=	manifestationService.findAvailability(manifestationDto);
		assertTrue(result);

		// Manifestation à tester du 25 au 27/06/2020
		manifestationDto.setDateDebut(date25Juin);
		// Fin 27/06/2020
		manifestationDto.setDateFin(date27Juin);
		// Resultat Test disponibilité = disponible / true
		result=	manifestationService.findAvailability(manifestationDto);
		assertTrue(result);

	}

	@Test
	@Order(8)  
	void testDelete() {
		// Test de la liste entités avant suppression ajout
		int size1 =  this.manifestationService.findAll().size();

		boolean result = false;
		assertFalse(result);
		
		//Suppression de l'objet test
		result =  this.manifestationService.delete(manifestationDtoTest.getId());
		assertTrue (result);

		// Test après suppression si taille de la liste entités -1
		int size2 =  this.manifestationService.findAll().size();
		assertTrue(size2<size1);
		assertTrue(size2==size1-1);

		System.out.println("Test this.manifestationService.delete(manifestationDtoTest.getId()) => "+this.manifestationService.findAll());
		System.out.println();

		// Suppression des entités test
		this.salleService.delete(salleTest.getId());	
		this.typeSalleService.delete(typeSalleTest.getId());
		this.animationService.delete(animationTest.getId());
		this.userService.delete(userTest.getId());
		this.roleService.delete(roleTest.getId());
	}

}
