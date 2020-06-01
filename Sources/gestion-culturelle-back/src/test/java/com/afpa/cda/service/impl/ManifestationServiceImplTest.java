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

import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.TypeSalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.service.IManifestationService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManifestationServiceImplTest {

	@Autowired
	IManifestationService manifestationService;

	RoleDto roleTest = new RoleDto (10,"ROLETEST");
	UserDto userTest = new UserDto (10,"USERTEST","USERTEST","1234",null,"cda@afpa.com", "Lille","10",null, roleTest,"Afpa"); 
	TypeSalleDto typeSalleTest = new TypeSalleDto (10,"TYPESALLETEST");
	SalleDto salleTest = new SalleDto (10,"SALLETEST",600,4,200,typeSalleTest);
	AnimationDto animationTest = new AnimationDto (10,"ANIMATIONTEST","SPORT",1500,500,userTest);
	@SuppressWarnings("deprecation")
	ManifestationDto manifestationDtoTest= new ManifestationDto(10,"MANIFESTATIONTEST",new Date(120,05,01),
			userTest,animationTest,new Date (120,05,15),new Date (120,05,19),0,salleTest,0,0,0);

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
		List <ManifestationDto> listManifestationsDto = null;
		assertNull(listManifestationsDto);

		listManifestationsDto =	this.manifestationService.findAll();
		assertNotNull(listManifestationsDto);
		assertNotEquals(0,listManifestationsDto.size());
		assertTrue(listManifestationsDto.size()>0);

		System.out.println("Test this.manifestationService.FindAll() => "+listManifestationsDto);

	}

	@Test
	@Order(2)  
	void testAdd() {
		System.out.println("Add manifestationDtoTest "+manifestationDtoTest);
		int size1 =  this.manifestationService.findAll().size();

		boolean result = true;
		assertTrue(result);
		result = this.manifestationService.add(manifestationDtoTest);
		assertFalse (result);

		int size2 = this.manifestationService.findAll().size();
		assertTrue(size2>size1);
		assertTrue(size2-1==size1);
		System.out.println("Test this.manifestationService.add(manifestationDtoTest) => "+this.manifestationService.findAll());


	}

	@Test
	@Order(3)  
	void testFindById() {
		ManifestationDto manifestationDto = null;
		assertNull(manifestationDto);

		manifestationDto = this.manifestationService.findById(manifestationDtoTest.getId());
		System.out.println("findbyid manifestationDto "+manifestationDto);
		
		assertNotNull(manifestationDto);
		//	assertEquals(manifestationDto.getId(), manifestationDtoTest.getId());
		assertEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());

		System.out.println("Test this.manifestationService.findById(manifestationDtoTest.getId()) => "+manifestationDto);

	}

	@Test
	@Order(4)  
	void testUpdate() {
		manifestationDtoTest = this.manifestationService.findById(manifestationDtoTest.getId());
		System.out.println("update manifestationDtoTest "+manifestationDtoTest);
		ManifestationDto manifestationDto = manifestationDtoTest;
		manifestationDto.setLabel("MANIFESATIONUPDATED");

		assertNotEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());

		boolean result = false;
		assertFalse(result);
		result = this.manifestationService.update(manifestationDto,manifestationDtoTest.getId());
		assertTrue (result);

		manifestationDtoTest = this.manifestationService.findById(manifestationDtoTest.getId());

		System.out.println("Test this.manifestationService.update(manifestationDto,manifestationDtoTest.getId()) => "+manifestationDtoTest);

		assertEquals(manifestationDto.getLabel(),manifestationDtoTest.getLabel());
	}

	@Test
	@Order(6)  
	void testCalcul() {

	}

	@Test
	@Order(7)  
	void testFindAvailability() {

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


	}

}
