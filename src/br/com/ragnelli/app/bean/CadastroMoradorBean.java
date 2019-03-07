package br.com.ragnelli.app.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.ragnelli.app.ejb.CadastroMoradorEJB;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Morador;

@SessionScoped
@Named("formMoradorBean")
public class CadastroMoradorBean implements Serializable {

	private Morador morador;
	private String passwordCheck;

	@EJB
	CadastroMoradorEJB cadastro;
	
	@Inject
	ExternalContext externalContext;
	
//	@Inject
//	Conversation conversation;
	
	@PostConstruct
	private void init() {
//		conversation.begin();
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
	
	public String gravar() {
		
		cadastro.gravar(morador);
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.invalidate();
		
		return "cadastrarMorador?faces-redirect=true";
	}
	
	//TODO: Criar método para validar a confirmação de senha
	
}
