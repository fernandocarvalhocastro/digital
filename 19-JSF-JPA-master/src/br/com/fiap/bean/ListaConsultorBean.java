package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.ConsultorBO;
import br.com.fiap.entity.Consultor;
import br.com.fiap.exception.DBException;

@ManagedBean
public class ListaConsultorBean {

	private List<Consultor> consultores;

	private ConsultorBO bo;
	
	@PostConstruct
	private void init(){
		bo = new ConsultorBO();
		consultores = bo.listar();	
	}
	
	public String excluir(int codigo){
		FacesMessage msg = null;
		try{
			bo.remover(codigo);
			msg = new FacesMessage("Excluido com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Falha ao excluir!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "lista-consultor?faces-redirect=true";
	}
	
	public List<Consultor> getConsultores() {
		return consultores;
	}

	public void setConsultores(List<Consultor> consultores) {
		this.consultores = consultores;
	}

}
