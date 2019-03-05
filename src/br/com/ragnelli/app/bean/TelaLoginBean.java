package br.com.ragnelli.app.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ragnelli.app.ejb.LoginBean;

@Named
@SessionScoped
public class TelaLoginBean implements Serializable {

//	private Integer moradorId;
	private String email;
	private String password;
	private UIComponent passwordText;

	@Inject
	private FacesContext context;

	@EJB
	private LoginBean login;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public UIComponent getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(UIComponent passwordText) {
		this.passwordText = passwordText;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String verificaSenha() {
		if (login.verificaSenha(email, password)) {

			return "cadastrarAvaliacao";

		}

		context.addMessage(passwordText.getClientId(context), new FacesMessage("Login ou senha incorretos!"));
		return null;
	}
}
