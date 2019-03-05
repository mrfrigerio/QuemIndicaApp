package br.com.ragnelli.app.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ragnelli.app.model.Morador;

@Stateless
public class LoginBean {

	@PersistenceContext
	private EntityManager em;

	public boolean verificaSenha(String email, String password) {

		Morador morador = getMoradorByEmail(email);

		if (morador != null && morador.getSenha().equals(password)) {

			return true;

		} else {

			return false;

		}

	}

	public Morador getMoradorByEmail(String email) {

		// Utilizando a Criteria API

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Morador> cq = cb.createQuery(Morador.class);
			Root<Morador> morador = cq.from(Morador.class);
			cq.select(morador);
//			cq.where(cb.equal(morador.get(Morador_.email), email));

			TypedQuery<Morador> q = em.createQuery(cq);

			Morador moradorFound = q.getSingleResult();

			return moradorFound;

		} catch (Exception e) {

			return null;

		}

		// Utilizando a JPQL
//		try{
//			
//			TypedQuery<Morador> q = em.createQuery(
//					                  "SELECT m FROM Morador m WHERE m.email = ?1", Morador.class)
//					                  .setParameter(1, email);
//			
//			Morador morador = q.getSingleResult();
//			return morador;
//			
//		} catch (Exception e) {
//			
//			return null;
//			
//		} 

	}

}
