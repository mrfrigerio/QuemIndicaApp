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

import br.com.ragnelli.app.ejb.CadastroUsuarioEJB;
import br.com.ragnelli.app.model.Bloco;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Usuario;

@ViewScoped
@Named("formUsuarioBean")
public class CadastroUsuarioBean implements Serializable {

	private Usuario usuario;
	private String passwordCheck;

	@EJB
	CadastroUsuarioEJB cadastro;

	@Inject
	ExternalContext externalContext;

//	@Inject
//	Conversation conversation;

	@PostConstruct
	private void init() {
//		conversation.begin();
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		usuario.setCondominio(condominio);
	}

	public List<Condominio> condominioAutocomplete(String nomeCondominio) {
		List<Condominio> listaDeCondominios = cadastro.buscarCondominioByName(nomeCondominio);
		return listaDeCondominios;
	}

	public List<Bloco> blocoAutocomplete(String nomeBloco) {

		Pattern pattern = Pattern.compile("(?i:.*" + nomeBloco + "+.*)");

		return usuario.getCondominio().getBlocos().stream()

				.filter(b -> {
					Matcher matcher = pattern.matcher(b.getNome());
					return matcher.matches();
				})

				.collect(Collectors.toList());
	}

	public String gravar() {

		cadastro.gravar(usuario);
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.invalidate();

		return "cadastrarUsuario?faces-redirect=true";
	}

}
