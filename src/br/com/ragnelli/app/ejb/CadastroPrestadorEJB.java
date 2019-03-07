package br.com.ragnelli.app.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ragnelli.app.model.Endereco;
import br.com.ragnelli.app.model.Prestador;
import br.com.ragnelli.app.webservice.WSConsumer;

@Stateless
public class CadastroPrestadorEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	public Endereco buscarCep(String cep) {
		Endereco endereco = WSConsumer.buscaEnderecoByCep(cep);
		return endereco;
	}
	
	public void gravar(Prestador prestador) {
		
		em.persist(prestador);
		
	}


}
