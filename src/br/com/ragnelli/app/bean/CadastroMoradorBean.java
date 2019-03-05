package br.com.ragnelli.app.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import br.com.ragnelli.app.ejb.CadastroMoradorEJB;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Morador;

@Named("formMoradorBean")
@RequestScoped
public class CadastroMoradorBean implements Serializable {

	private Morador morador;
	private String passwordCheck;

	@EJB
	CadastroMoradorEJB cadastro;

	@PostConstruct
	private void init() {
	}

	public Morador getMorador() {
		if(morador == null) {
			morador = new Morador();
		}
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	
	
	public String getPasswordCheck() {
		if(passwordCheck == null) {
			passwordCheck = "";
		}
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public List<Condominio> getCondominios() {
		return cadastro.buscarCondominios();
	}

	public void carregarBlocos(ValueChangeEvent event) {
		Condominio condominio = cadastro.buscarCondominioById((Integer) event.getNewValue());
		morador.setCondominio(condominio);
	}
}
