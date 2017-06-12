package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.EspecialidadeBO;
import br.com.fiap.entity.Especialidade;
import br.com.fiap.exception.DBException;

@ManagedBean
public class CadastrarEspecialidadeBean {

	private Especialidade especialidade;
	private EspecialidadeBO bo;

	@PostConstruct
	public void init() {
		especialidade = new Especialidade();
		bo = new EspecialidadeBO();
	}
	
	public String cadastrar(){
		FacesMessage msg = null;
		try {
			if(especialidade.getCodigo()==0){
				bo.cadastrar(especialidade);
				msg = new FacesMessage("Cadastrado com sucesso");
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
			}else{
				bo.atualizar(especialidade);
				msg = new FacesMessage("Atualizado com sucesso");
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
			}	
		} catch (DBException e) {
			msg = new FacesMessage("Falha ao realizar operação");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			e.printStackTrace();
			return "especialidade";
		}
		//manter a mensagem após o redirect (nova requisição)
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	    return "lista-especialidade?faces-redirect=true";
	}
	

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}
