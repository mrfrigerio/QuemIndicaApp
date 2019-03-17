package br.com.ragnelli.app.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ragnelli.app.model.Bloco;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Usuario;

@Stateless
public class CadastroUsuarioEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<Condominio> buscarCondominios(){
		return em.createQuery("SELECT c from Condominio c", Condominio.class).getResultList();	
	}
	
	public Condominio buscarCondominioById(Integer condominioId) {
		return em.find(Condominio.class, condominioId);
	}
	
	public List<Condominio> buscarCondominioByName(String nomeCondominio) {
		return em.createQuery("SELECT c FROM Condominio c WHERE c.nome LIKE :nomeCondominio", Condominio.class)
				.setParameter("nomeCondominio", "%"+ nomeCondominio + "%")
				.getResultList();
	}
	
	public void gravar(Usuario usuario) {
		
		em.persist(usuario);
		
	}

	public Bloco buscarBlocoById(Integer blocoId) {
		return em.find(Bloco.class, blocoId);
	}

	public List<Bloco> buscarBlocoPorNome(String nomeBloco) {
		return em.createQuery("SELECT b FROM Bloco c WHERE b.nome LIKE :nomeBloco", Bloco.class)
				.setParameter("nomeBloco", "%"+ nomeBloco + "%")
				.getResultList();
	}

	

}
