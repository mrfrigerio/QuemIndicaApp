package br.com.ragnelli.app.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ragnelli.app.model.Prestador;

@Stateless
public class PrestadoresBean {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<Prestador> listar() {
		return em.createQuery("SELECT p FROM Prestador p", Prestador.class).getResultList();
	}

}
