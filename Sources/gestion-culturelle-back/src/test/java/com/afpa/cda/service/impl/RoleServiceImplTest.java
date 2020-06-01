package com.afpa.cda.service.impl;
import static org.junit.Assert.*;

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

import com.afpa.cda.dto.RoleDto;
import com.afpa.cda.service.IRoleService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleServiceImplTest {

	@Autowired
	IRoleService roleService;

	RoleDto roleDtoTest = new RoleDto (10,"ROLETEST");


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

		List <RoleDto> listRolesDto = null;
		assertNull(listRolesDto);

		listRolesDto =	this.roleService.findAll();
		assertNotNull(listRolesDto);
		assertNotEquals(0,listRolesDto.size());
		assertTrue(listRolesDto.size()>0);

		System.out.println("Test this.roleService.FindAll() => "+listRolesDto);

	}

	@Test
	@Order(2)  
	void testAdd() {
		int size1 =  this.roleService.findAll().size();

		boolean result = true;
		assertTrue(result);
		result = this.roleService.add(roleDtoTest);
		assertFalse (result);

		int size2 = this.roleService.findAll().size();
		assertTrue(size2>size1);
		assertTrue(size2-1==size1);
		System.out.println("Test this.roleService.add(roleDtoTest) => "+this.roleService.findAll());

	}

	@Test
	@Order(3)  
	void testFindById() {
		RoleDto roleDto = null;
		assertNull(roleDto);

		roleDto = this.roleService.findById(roleDtoTest.getId());

		assertNotNull(roleDto);
	//	assertEquals(roleDto.getId(), roleDtoTest.getId());
		assertEquals(roleDto.getLabel(),roleDtoTest.getLabel());

		System.out.println("Test this.roleService.findById(roleDtoTest.getId()) => "+roleDto);
	}


	@Test
	@Order(4)  
	void testUpdate() {
		roleDtoTest = this.roleService.findById(roleDtoTest.getId());

		RoleDto roleDto = new RoleDto (roleDtoTest.getId(),"ROLEUPDATED");

		assertNotEquals(roleDto.getLabel(),roleDtoTest.getLabel());

		boolean result = false;
		assertFalse(result);
		result = this.roleService.update(roleDto,roleDtoTest.getId());
		assertTrue (result);

		roleDtoTest = this.roleService.findById(roleDtoTest.getId());

		System.out.println("Test this.roleService.update(roleDto,roleDtoTest.getId()) => "+roleDtoTest);

		assertEquals(roleDto.getLabel(),roleDtoTest.getLabel());

	}

	@Test
	@Order(5)  
	void testDelete() {
		int size1 =  this.roleService.findAll().size();

		boolean result = false;
		assertFalse(result);
		result =  this.roleService.delete(roleDtoTest.getId());
		assertTrue (result);

		int size2 =  this.roleService.findAll().size();
		assertTrue(size2<size1);
		assertTrue(size2==size1-1);

		System.out.println("Test this.roleService.delete(roleDtoTest.getId()) => "+this.roleService.findAll());

	}


}
