package br.com.ragnelli.app.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ragnelli.app.model.Avaliacao;
import br.com.ragnelli.app.model.Avaliacao.TipoAvaliacao;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Morador;
import br.com.ragnelli.app.model.Prestador;

@Stateless
public class CadastroMoradorEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<Condominio> buscarCondominios(){
		return em.createQuery("SELECT c from Condominio c", Condominio.class).getResultList();	
	}
	
	public void gravar(Date data, Integer moradorId, TipoAvaliacao tipo, Integer prestadorId, String motivo, Double nota) {
		
		Morador morador = em.find(Morador.class, moradorId);
		Prestador prestador = em.find(Prestador.class, prestadorId);
		
		Avaliacao avaliacao = new Avaliacao(data, morador, tipo, prestador, motivo, nota);
		
		em.persist(avaliacao);
		
	}
	
	public Condominio buscarCondominioById(Integer condominioId) {
		return em.find(Condominio.class, condominioId);
	}

}
