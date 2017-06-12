package br.com.fiap.dao.impl;

import br.com.fiap.entity.Especialidade;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EspecialidadeDAO;

public class EspecialidadeDAOImpl 
		extends GenericDAOImpl<Especialidade, Integer>
					implements EspecialidadeDAO{

	public EspecialidadeDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Especialidade> listar() {
		return em.createQuery("from Especialidade",Especialidade.class).getResultList();
	}

}
