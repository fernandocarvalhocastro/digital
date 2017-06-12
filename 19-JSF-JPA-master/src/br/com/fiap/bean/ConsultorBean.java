package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.ConsultorBO;
import br.com.fiap.entity.Consultor;
import br.com.fiap.exception.DBException;

@ManagedBean
public class ConsultorBean {
	private Consultor consultor;

	private ConsultorBO bo;
	
	@PostConstruct //inicializar os objetos (Pode ter qualquer nome de m�todo, o importante � a anota��o!!!!
	private void init(){
		consultor = new Consultor();
		bo = new ConsultorBO();
	}
	
	public String cadastrar(){
		FacesMessage msg;
		try {
			if(consultor.getCodigo()==0){
				bo.cadastrar(consultor);
				msg = new FacesMessage("Cadastrado com sucesso!");
			}else{
				bo.atualizar(consultor);
				msg = new FacesMessage("Alterado com sucesso!");
			}
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (DBException e) {
			msg = new FacesMessage("Deu Ruim!!!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
			return "index";
		}
		//manter a mensagem ap�s o redirect (nova requisi��o)
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "lista-consultor?faces-redirect=true";
	}
	
	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}
}
