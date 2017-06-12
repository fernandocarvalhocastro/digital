package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.to.Produto;

@ManagedBean
public class ProdutoBean {

	private Produto produto;
	
	private List<Produto> lista;
	
	private ProdutoRepository rep;
	
	@PostConstruct
	private void init(){
		rep = new ProdutoRepository();
		produto = new Produto();
		try {
			lista = rep.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(){
		FacesMessage msg;
		try {
			rep.cadastrar(produto);
			msg = new FacesMessage("Sucesso!");
			lista = rep.listar();
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}
	
}
