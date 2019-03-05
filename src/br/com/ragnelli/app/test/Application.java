package br.com.ragnelli.app.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import br.com.ragnelli.app.model.Endereco;

public class Application {

	public static void main(String[] args) {
		
		System.out.println(buscaCep("08070130"));

	}

	public static Endereco buscaCep(String cep) {
		Client client = ClientBuilder.newClient();
		Endereco endereco = client.target("http://viacep.com.br/ws/")
				.path(cep + "/json")
				.request(MediaType.APPLICATION_JSON)
				.get(Endereco.class);
		return endereco;
	}
}
