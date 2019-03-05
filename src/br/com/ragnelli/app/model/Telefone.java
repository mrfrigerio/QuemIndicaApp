package br.com.ragnelli.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone implements Serializable {

	@Column(length = 2)
	private String dddTelefoneFixo;
	
	@Column(length = 9)
	private String numeroTelefoneFixo;
	
	@Column(length = 2)
	private String dddTelefoneCelular;
	
	@Column(length = 10)
	private String numeroTelefoneCelular;

	public String getDddTelefoneFixo() {
		return dddTelefoneFixo;
	}

	public void setDddTelefoneFixo(String dddTelefoneFixo) {
		this.dddTelefoneFixo = dddTelefoneFixo;
	}

	public String getNumeroTelefoneFixo() {
		return numeroTelefoneFixo;
	}

	public void setNumeroTelefoneFixo(String numeroTelefoneFixo) {
		this.numeroTelefoneFixo = numeroTelefoneFixo;
	}

	public String getDddTelefoneCelular() {
		return dddTelefoneCelular;
	}

	public void setDddTelefoneCelular(String dddTelefoneCelular) {
		this.dddTelefoneCelular = dddTelefoneCelular;
	}

	public String getNumeroTelefoneCelular() {
		return numeroTelefoneCelular;
	}

	public void setNumeroTelefoneCelular(String numeroTelefoneCelular) {
		this.numeroTelefoneCelular = numeroTelefoneCelular;
	}
	
}
