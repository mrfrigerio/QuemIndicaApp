package br.com.ragnelli.app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ragnelli.app.ejb.ApplicationEJB;
import br.com.ragnelli.app.ejb.CadastroCondominioEJB;
import br.com.ragnelli.app.exception.CadastroException;
import br.com.ragnelli.app.model.Bloco;
import br.com.ragnelli.app.model.Condominio;
import br.com.ragnelli.app.model.Condominio.TipoCondominio;
import br.com.ragnelli.app.model.Endereco;

@Named("formCondominioBean")
@ConversationScoped
public class CadastroCondominioBean implements Serializable {

	@EJB
	CadastroCondominioEJB cadastro;
	
	@EJB
	ApplicationEJB appEJB;
	
	@Inject
	Conversation conversation;

	@Inject
	FacesContext context;

	private Condominio condominio;
	private Bloco blocoBean;
	private List<Bloco> blocosBean;

	@PostConstruct
	private void init() {
		conversation.begin();
		condominio = new Condominio();
		blocosBean = new ArrayList<>();
		blocoBean = new Bloco("");
	}

	public Condominio getCondominio() {
		if (condominio == null) {
			condominio = new Condominio();
		}
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public TipoCondominio[] getTipos() {
		return TipoCondominio.values();
	}
	
	public Bloco getBlocoBean() {
		return blocoBean;
	}

	public void setBlocoBean(Bloco blocoBean) {
		this.blocoBean = blocoBean;
	}

	public List<Bloco> getBlocosBean() {
		return blocosBean;
	}

	public void setBlocosBean(List<Bloco> blocosBean) {
		this.blocosBean = blocosBean;
	}

	public String adicionarBloco() {
		if (!blocoBean.getNome().isBlank()) {
			blocosBean.add(blocoBean);
			blocoBean = new Bloco("");
		}

		return null;
	}
	public String removerBloco(Bloco bloco) {
		blocosBean.remove(bloco);
		return null;
	}
	
	public List<String> getEstados(String nomeEstado){
		
		Pattern pattern = Pattern.compile("(?i:.*" + nomeEstado + "+.*)");
		return appEJB.getEstados().stream()
				.filter(e -> {
					Matcher matcher = pattern.matcher(e);
					return matcher.matches();
				})
				.collect(Collectors.toList());
	}

	public String gravar() {

		try {
			cadastro.verificaCondominioJaCadastrado(condominio);
			adicionarBloco();
			cadastro.gravar(condominio, blocosBean);
			FacesMessage msg = new FacesMessage("Condomínio Cadastrado com Sucesso!!!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, msg);
			return limpar();

		} catch (CadastroException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			return null;
		}

	}
	
	public String limpar() {
		conversation.end();
		init();
		return null;
		
//		return "cadastrarCondominio?faces-redirect=true";
	}

	public String buscaCep(AjaxBehaviorEvent event) {
		
		try {
			Endereco end = cadastro.buscarCep(condominio.getEndereco().getCep());
			condominio.getEndereco().setCep(end.getCep());
			condominio.getEndereco().setLogradouro(end.getLogradouro());
			condominio.getEndereco().setBairro(end.getBairro());
			condominio.getEndereco().setNomeEstado(end.getNomeEstado());

		} catch (Exception e) {
			System.out.println("CEP não localizado!");
		}

		return null;
	}

}
