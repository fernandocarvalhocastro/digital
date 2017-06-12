package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ConsultorDAO;
import br.com.fiap.dao.EspecialidadeDAO;
import br.com.fiap.dao.impl.ConsultorDAOImpl;
import br.com.fiap.dao.impl.EspecialidadeDAOImpl;
import br.com.fiap.entity.Consultor;
import br.com.fiap.entity.Especialidade;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

public class EspecialidadeBO {

	private EntityManagerFactory fabrica = 
			EntityManagerFactorySingleton.getInstance();
	
	public List<Especialidade> listar(){
		EntityManager em = fabrica.createEntityManager();
		EspecialidadeDAO dao = new EspecialidadeDAOImpl(em);
		List<Especialidade> lista = dao.listar();
		em.close();
		return lista;
	}

	public Especialidade buscar(int codigo) {
		EntityManager em = fabrica.createEntityManager();
		EspecialidadeDAO dao = new EspecialidadeDAOImpl(em);
		Especialidade consultor = dao.pesquisar(codigo);
		em.close();
		return consultor;
	}

	public void cadastrar(Especialidade consultor) throws DBException {
		EntityManager em = fabrica.createEntityManager();
		EspecialidadeDAO dao = new EspecialidadeDAOImpl(em);
		dao.cadastrar(consultor);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Erro");
		}finally {
			em.close();
		}
	}

	public void atualizar(Especialidade consultor) throws DBException {
		EntityManager em = fabrica.createEntityManager();
		EspecialidadeDAO dao = new EspecialidadeDAOImpl(em);
		dao.alterar(consultor);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Erro");
		}finally {
			em.close();
		}
	}

	public void remover(int codigo) throws DBException {
		EntityManager em = fabrica.createEntityManager();
		EspecialidadeDAO dao = new EspecialidadeDAOImpl(em);
		try {
			dao.remover(codigo);
			dao.salvar();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Erro");
		} finally {
			em.close();
		}
	}
	
}
