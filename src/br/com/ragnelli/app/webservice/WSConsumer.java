package br.com.ragnelli.app.webservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import br.com.ragnelli.app.model.Endereco;

public class WSConsumer {

	public static Endereco buscaEnderecoByCep(String cep) {
		Client client = ClientBuilder.newClient();
		
		if (cep != null && !cep.isBlank()) {
			Endereco endereco = client.target("http://viacep.com.br/ws/")
									  .path(cep + "/json")
									  .request(MediaType.APPLICATION_JSON)
									  .get(Endereco.class);
			return endereco;
		}
		
		return null;
	}
}
