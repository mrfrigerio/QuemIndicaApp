package br.com.ragnelli.app.bean;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.ragnelli.app.ejb.CadastroMoradorEJB;
import br.com.ragnelli.app.model.Bloco;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Morador;

@ViewScoped
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
		if (morador == null) {
			morador = new Morador();
		}
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public String getPasswordCheck() {
		if (passwordCheck == null) {
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

	public void carregarBlocos(AjaxBehaviorEvent event) {
		Condominio condominio = (Condominio) event.getSource();
		morador.setCondominio(condominio);
	}

	public List<Condominio> condominioAutocomplete(String nomeCondominio) {
		List<Condominio> listaDeCondominios = cadastro.buscarCondominioByName(nomeCondominio);
		return listaDeCondominios;
	}

	public List<Bloco> blocoAutocomplete(String nomeBloco) {

		Pattern pattern = Pattern.compile("(?i:.*" + nomeBloco + "+.*)");

		return morador.getCondominio().getBlocos().stream()

				.filter(b -> {
					Matcher matcher = pattern.matcher(b.getNome());
					return matcher.matches();
				})

				.collect(Collectors.toList());
	}

	public String gravar() {

		cadastro.gravar(morador);
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.invalidate();

		return "cadastrarMorador?faces-redirect=true";
	}

}
