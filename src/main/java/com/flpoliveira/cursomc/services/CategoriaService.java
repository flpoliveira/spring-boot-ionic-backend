package com.flpoliveira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flpoliveira.cursomc.domain.Categoria;
import com.flpoliveira.cursomc.repositories.CategoriaRepository;
import com.flpoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService 
{
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) 
	{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj)
	{
		obj.setId(null);
		return repo.save(obj);
	}
}
