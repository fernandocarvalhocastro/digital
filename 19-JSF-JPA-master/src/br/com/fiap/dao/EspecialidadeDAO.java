package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Especialidade;

public interface EspecialidadeDAO extends GenericDAO<Especialidade, Integer>{

	List<Especialidade> listar();
	
}
