package br.com.ragnelli.app.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ragnelli.app.model.Usuario;

@Stateless
public class LoginBean {

	@PersistenceContext
	private EntityManager em;

	public boolean verificaSenha(String email, String password) {

		Usuario usuario = getUsuarioByEmail(email);

		if (usuario != null && usuario.getSenha().equals(password)) {

			return true;

		} else {

			return false;

		}

	}

	public Usuario getUsuarioByEmail(String email) {

		// Utilizando a Criteria API

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> usuario = cq.from(Usuario.class);
			cq.select(usuario);
//			cq.where(cb.equal(morador.get(Morador_.email), email));

			TypedQuery<Usuario> q = em.createQuery(cq);

			Usuario usuarioFound = q.getSingleResult();

			return usuarioFound;

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
