package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import br.com.fiap.bo.EspecialidadeBO;
import br.com.fiap.entity.Especialidade;
import br.com.fiap.exception.DBException;

@ManagedBean
public class ListaEspecialidadeBean {

	private List<Especialidade> especialidades;
	private EspecialidadeBO bo;
	
	@PostConstruct
	public void init(){
		bo = new EspecialidadeBO();
		especialidades = bo.listar();
	}
	
	public String apagar(int codigo){
		FacesMessage msg = null;
		try {
			bo.remover(codigo);
			msg = new FacesMessage("Removido com sucesso.");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (DBException e) {
			msg = new FacesMessage("Removido com sucesso.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "lista-especialidade?faces-redirect=true";
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
}
