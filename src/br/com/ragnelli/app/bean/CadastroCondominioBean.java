package br.com.ragnelli.app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;

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

	@Inject
	Conversation conversation;

	@Inject
	FacesContext context;

	private Condominio condominio;

	private List<Bloco> blocosBean;
	private ListDataModel<Bloco> blocosModelBean;

	private String nomeBlocoBean;
	private List<Integer> unidadesBlocoBean;

	@PostConstruct
	private void init() {
		conversation.begin();
		blocosBean = new ArrayList<>();
		blocosModelBean = new ListDataModel<>(blocosBean);
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

	public List<Bloco> getBlocosBean() {
		if (blocosBean == null) {
			blocosBean = new ArrayList<>();
		}
		return blocosBean;
	}

	public void setBlocosBean(List<Bloco> blocosBean) {
		this.blocosBean = blocosBean;
	}

	public String getNomeBlocoBean() {
		return nomeBlocoBean;
	}

	public void setNomeBlocoBean(String nomePrimeiroBlocoBean) {
		this.nomeBlocoBean = nomePrimeiroBlocoBean;
	}

	public List<Integer> getUnidadesBlocoBean() {
		if (unidadesBlocoBean == null) {
			unidadesBlocoBean = new ArrayList<>();
		}

		return unidadesBlocoBean;
	}

	public void setUnidadesBlocoBean(List<Integer> unidadesPrimeiroBloco) {
		this.unidadesBlocoBean = unidadesPrimeiroBloco;
	}

	public ListDataModel<Bloco> getBlocosModelBean() {
		return blocosModelBean;
	}

	public void setBlocosModelBean(ListDataModel<Bloco> blocosModelBean) {
		this.blocosModelBean = blocosModelBean;
	}

	public String adicionarBloco() {
		if (!nomeBlocoBean.isBlank()) {
			Bloco bloco = new Bloco(nomeBlocoBean, unidadesBlocoBean);
			blocosBean.add(bloco);
			nomeBlocoBean = null;
			unidadesBlocoBean = null;
		}

		return null;
	}

	public String gravar() {

		try {
			cadastro.verificaCondominioJaCadastrado(condominio);
			adicionarBloco();
			cadastro.gravar(condominio, blocosBean);
			FacesMessage msg = new FacesMessage("Condomínio Cadastrado com Sucesso!!!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, msg);
			conversation.end();
			return "cadastrarCondominio?faces-redirect=true";

		} catch (CadastroException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
			return null;
		}

	}
	
	public String limpar() {
		conversation.end();
		return "cadastrarCondominio?faces-redirect=true";
	}

	public String buscaCep(AjaxBehaviorEvent event) {
		try {
			Endereco end = cadastro.buscarCep(condominio.getEndereco().getCep());
			condominio.getEndereco().setCep(end.getCep());
			condominio.getEndereco().setLogradouro(end.getLogradouro());
			condominio.getEndereco().setBairro(end.getBairro());
			condominio.getEndereco().setEstado(end.getEstado());

		} catch (Exception e) {
			System.out.println("CEP não localizado!");
		}

		return null;
	}

}
