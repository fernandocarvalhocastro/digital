package br.com.fiap.repository;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.to.Produto;

public class ProdutoRepository {

	private static final String URL = 
			"http://localhost:8080/CorrecaoNACServer/rest/produto/";
	
	private Client client = Client.create();
	
	public void cadastrar(Produto produto) throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse response =	
				resource.type(MediaType.APPLICATION_JSON)
									.post(ClientResponse.class,produto);
		if (response.getStatus() != 201)
			throw new Exception("Erro!");
	}
	
	public void alterar(Produto produto) throws Exception{
		WebResource resource = client.resource(URL + produto.getCodigo());
		ClientResponse response = 
				resource.type(MediaType.APPLICATION_JSON)
									.put(ClientResponse.class,produto);
		if (response.getStatus() != 200)
			throw new Exception("Erro!");
	}
	
	public void remover(int codigo) throws Exception{
		WebResource resource = client.resource(URL + codigo);
		ClientResponse response =
				resource.delete(ClientResponse.class);
		if (response.getStatus() != 204)
			throw new Exception("Erro!");
	}
	
	public List<Produto> listar() throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse response = resource
				.accept(MediaType.APPLICATION_JSON)
									.get(ClientResponse.class);
		if (response.getStatus() != 200)
			throw new Exception("Erro!");
		return Arrays.asList(response.getEntity(Produto[].class));
	}
	
}
