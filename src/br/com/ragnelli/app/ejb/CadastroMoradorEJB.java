package br.com.ragnelli.app.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ragnelli.app.model.Bloco;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Morador;

@Stateless
public class CadastroMoradorEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<Condominio> buscarCondominios(){
		return em.createQuery("SELECT c from Condominio c", Condominio.class).getResultList();	
	}
	
	public Condominio buscarCondominioById(Integer condominioId) {
		return em.find(Condominio.class, condominioId);
	}
	
	public void gravar(Morador morador) {
		
		em.persist(morador);
		
	}

	public Bloco buscarBlocoById(Integer blocoId) {
		return em.find(Bloco.class, blocoId);
	}
	

}
