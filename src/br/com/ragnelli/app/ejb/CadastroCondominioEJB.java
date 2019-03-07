package br.com.ragnelli.app.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ragnelli.app.exception.CadastroException;
import br.com.ragnelli.app.model.Bloco;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Endereco;
import br.com.ragnelli.app.webservice.WSConsumer;

@Stateless
public class CadastroCondominioEJB {

	@PersistenceContext
	private EntityManager em;
	
	public Endereco buscarCep(String cep) {
		Endereco endereco = WSConsumer.buscaEnderecoByCep(cep);
		return endereco;
	}
	
	public void buscarCondominio(Condominio condominio) throws  CadastroException {
		
		List<Condominio> condominios = em.createQuery("SELECT c FROM Condominio c ORDER BY c.nome", Condominio.class).getResultList();
		
		if(condominios.contains(condominio)) {
			throw new CadastroException("Já existe um condomínio cadastrado com este endereço!");
		}
	}
	
	public void gravar(Condominio condominio, List<Bloco> blocos) {
		
		em.persist(condominio);
		
		blocos.sort((e1, e2) -> e1.getNome().compareTo(e2.getNome()));
		
		for(Bloco bloco: blocos) {
			em.persist(bloco);
			bloco.setCondominio(condominio);
		}
	}

}
