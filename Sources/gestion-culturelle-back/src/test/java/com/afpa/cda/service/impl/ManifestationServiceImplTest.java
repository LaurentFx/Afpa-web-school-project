package com.afpa.cda.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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

	static RoleDto roleTest = new RoleDto (10,"ROLETEST");
	static UserDto userTest = new UserDto (10,"USERTEST","USERTEST","1234",null,"cda@afpa.com", "Lille","10",null, roleTest,"Afpa"); 
	static TypeSalleDto typeSalleTest = new TypeSalleDto (10,"TYPESALLETEST");
	static SalleDto salleTest = new SalleDto (10,"SALLETEST",600,4,200,typeSalleTest);
	static AnimationDto animationTest = new AnimationDto (10,"ANIMATIONTEST","SPORT",1500,500,userTest);
	@SuppressWarnings("deprecation")
	static ManifestationDto manifestationDtoTest= new ManifestationDto(10,"MANIFESTATIONTEST",new Date(120,05,01),
			userTest,animationTest,new Date (120,05,15),new Date (120,05,19),0,salleTest,0,0,0);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//		roleService.add(roleTest);
		//		userService.add(userTest);
		//		animationService.add(animationTest);
		//	typeSalleService.add(typeSalleTest);
		//		salleService.add(salleTest);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//				roleService.delete(roleTest.getId());
		//				userService.delete(userTest.getId());
		//				animationService.delete(animationTest.getId());
		//				typeSalleService.delete(typeSalleTest.getId());
		//				salleService.delete(salleTest.getId());
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
		List <ManifestationDto> listManifestationsDto = null;
		assertNull(listManifestationsDto);

		listManifestationsDto =	this.manifestationService.findAll();
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
		roleTest.setId(this.roleService.add(roleTest));
		typeSalleTest.setId(this.typeSalleService.add(typeSalleTest));
		userTest.setId(this.userService.add(userTest));
		salleTest.setId(this.salleService.add(salleTest));	
		animationTest.setId(this.animationService.add(animationTest));

		int size1 =  this.manifestationService.findAll().size();
		int id = 0;
		assertEquals(0,id);
		id=this.manifestationService.add(manifestationDtoTest);
		manifestationDtoTest.setId(id);
		assertNotEquals (0,id);
		int size2 = this.manifestationService.findAll().size();
		assertTrue(size2>size1);
		assertTrue(size2-1==size1);

		System.out.println("Test this.manifestationService.add(manifestationDtoTest) => "+this.manifestationService.findAll());
		System.out.println();
	}

	@Test
	@Order(3)  
	void testFindById() {
		ManifestationDto manifestationDto = null;
		assertNull(manifestationDto);

		manifestationDto = this.manifestationService.findById(manifestationDtoTest.getId());
		assertNotNull(manifestationDto);
		assertEquals(manifestationDto.getId(), manifestationDtoTest.getId());
		assertEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());
		System.out.println("Test this.manifestationService.findById(manifestationDtoTest.getId()) => "+manifestationDtoTest);
		System.out.println();
	}

	@SuppressWarnings("deprecation")
	@Test
	@Order(4)  
	void testUpdate() {
		ManifestationDto manifestationDto = new ManifestationDto(10,"MANIFESTATIONTEST",new Date(120,05,01),
				userTest,animationTest,new Date (120,05,15),new Date (120,05,19),0,salleTest,0,0,0);
		manifestationDto.setLabel("MANIFESATIONUPDATED");

		assertNotEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());
		boolean result = false;
		assertFalse(result);
		result = this.manifestationService.update(manifestationDto,manifestationDtoTest.getId());
		assertTrue (result);
		manifestationDtoTest = this.manifestationService.findById(manifestationDtoTest.getId());
		assertEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());

		System.out.println("Test this.manifestationService.update(manifestationDto,manifestationDtoTest.getId()) => "+manifestationDtoTest);
		System.out.println();
	}

	@Test
	@Order(6)  
	void testCalcul() {
		manifestationDtoTest.setReservations(0);
		manifestationDtoTest.setReservationsVip(0);
		manifestationDtoTest.setCout(0);
		manifestationDtoTest.setPrixBillet(0);

		assertEquals(0,manifestationDtoTest.getReservations());
		assertEquals(0,manifestationDtoTest.getReservationsVip());
		assertEquals(0,manifestationDtoTest.getCout(),1);
		assertEquals(0,manifestationDtoTest.getPrixBillet(),1);
		manifestationDtoTest=this.manifestationService.calcul(manifestationDtoTest);

		assertNotEquals(0,manifestationDtoTest.getReservations());
		assertNotEquals(0,manifestationDtoTest.getReservationsVip());
		assertNotEquals(0,manifestationDtoTest.getCout(),1);
		assertNotEquals(0,manifestationDtoTest.getPrixBillet(),1);

		System.out.println("Test this.manifestationService.calcul() => "+manifestationDtoTest);
		System.out.println();
	}

	@SuppressWarnings("deprecation")
	@Test
	@Order(7)  
	void testFindAvailability() {
		boolean result=true;
		assertTrue(result);

		// Début 15/06/2020
		manifestationDtoTest.setDateDebut(new Date (120,05,15));
		// Fin 19/06/2020
		manifestationDtoTest.setDateFin(new Date (120,05,19));
		ManifestationDto manifestationDto = new ManifestationDto(10,"MANIFESTATIONTEST",new Date(120,05,01),
				userTest,animationTest,new Date (120,05,15),new Date (120,05,19),0,salleTest,0,0,0);

		// Début 13/06/2020
		manifestationDto.setDateDebut(new Date (120,05,13));
		// Fin 15/06/2020
		manifestationDto.setDateFin(new Date (120,05,15));
		// Test indisponible = false
		result=	manifestationService.findAvailability(manifestationDto);
		assertFalse(result);

		// Début 19/06/2020
		manifestationDto.setDateDebut(new Date (120,05,19));
		// Fin 21/06/2020
		manifestationDto.setDateFin(new Date (120,05,21));
		// Test indisponible = false
		result=	manifestationService.findAvailability(manifestationDto);
		assertFalse(result);

		// Début 14/06/2020
		manifestationDto.setDateDebut(new Date (120,05,14));
		// Fin 20/06/2020
		manifestationDto.setDateFin(new Date (120,05,20));
		// Test indisponible = false
		result=	manifestationService.findAvailability(manifestationDto);
		assertFalse(result);

		// Début 11/06/2020
		manifestationDto.setDateDebut(new Date (120,05,11));
		// Fin 13/06/2020
		manifestationDto.setDateFin(new Date (120,05,13));
		// Test disponible = True
		result=	manifestationService.findAvailability(manifestationDto);
		assertTrue(result);

		// Début 25/06/2020
		manifestationDto.setDateDebut(new Date (120,05,25));
		// Fin 27/06/2020
		manifestationDto.setDateFin(new Date (120,05,27));
		// Test disponible = True
		result=	manifestationService.findAvailability(manifestationDto);
		assertTrue(result);

		System.out.println("Test this.manifestationService.indAvailability(manifestationDto) => "+result);
		System.out.println();

	}


	@Test
	@Order(8)  
	void testDelete() {
		int size1 =  this.manifestationService.findAll().size();

		boolean result = false;
		assertFalse(result);
		result =  this.manifestationService.delete(manifestationDtoTest.getId());
		assertTrue (result);

		int size2 =  this.manifestationService.findAll().size();
		assertTrue(size2<size1);
		assertTrue(size2==size1-1);

		System.out.println("Test this.manifestationService.delete(manifestationDtoTest.getId()) => "+this.manifestationService.findAll());
		System.out.println();

		this.animationService.delete(animationTest.getId());
		this.userService.delete(userTest.getId());
		this.salleService.delete(salleTest.getId());	
		this.roleService.delete(roleTest.getId());
		this.typeSalleService.delete(typeSalleTest.getId());
	}

}
