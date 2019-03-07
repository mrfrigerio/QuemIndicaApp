package br.com.ragnelli.app.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ragnelli.app.ejb.CadastroPrestadorEJB;
import br.com.ragnelli.app.model.Endereco;
import br.com.ragnelli.app.model.Prestador;

@RequestScoped
@Named("formPrestadorBean")
public class CadastroPrestadorBean implements Serializable {

	private Prestador prestador;

	@EJB
	CadastroPrestadorEJB cadastro;
	
	@Inject
	ExternalContext externalContext;
	
	@PostConstruct
	private void init() {
	}
	
	
	
	public Prestador getPrestador() {
		if(prestador == null) {
			prestador = new Prestador();
		}
		return prestador;
	}


	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public String buscaCep(AjaxBehaviorEvent event) {
		try {
			Endereco end = cadastro.buscarCep(prestador.getEndereco().getCep());
			prestador.getEndereco().setCep(end.getCep());
			prestador.getEndereco().setLogradouro(end.getLogradouro());
			prestador.getEndereco().setBairro(end.getBairro());
			prestador.getEndereco().setEstado(end.getEstado());

		} catch (Exception e) {
			System.out.println("CEP não localizado!");
		}

		return null;
	}

	public String gravar() {
		
		cadastro.gravar(prestador);
//		HttpSession session = (HttpSession) externalContext.getSession(false);
//		session.invalidate();
		
		return "cadastrarPrestador?faces-redirect=true";
	}
	
}
