package com.afpa.cda.dao;

import java.util.List;
import java.util.Optional;

import com.afpa.cda.entity.Personne;

public class PersonneDao implements PersonneRepository  {

	@Override
	public <S extends Personne> S save(S entity) {
	
		return null;
	}

	@Override
	public <S extends Personne> Iterable<S> saveAll(Iterable<S> entities) {
		
		return null;
	}

	@Override
	public Optional<Personne> findById(Integer id) {
		
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
	
		return false;
	}

	@Override
	public Iterable<Personne> findAllById(Iterable<Integer> ids) {
	
		return null;
	}

	@Override
	public long count() {
	
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
	
		
	}

	@Override
	public void delete(Personne entity) {
		
		
	}

	@Override
	public void deleteAll(Iterable<? extends Personne> entities) {

		
	}

	@Override
	public void deleteAll() {
		
		
	}

	@Override
	public List<Personne> findAll() {
	
		return null;
	}

}
