package br.com.ragnelli.app.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Morador extends Pessoa implements Serializable {

	private String senha;

	@ManyToOne
	@JoinColumn(name = "condominio_id", nullable = false)
	private Condominio condominio;
	
	@ManyToOne
	@JoinColumn(name = "bloco_id")
	private Bloco bloco;

	@Column(nullable = false)
	private Integer unidade;

	@OneToMany(mappedBy = "morador")
	private Set<Avaliacao> avaliacoes;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Bloco getBloco() {
		if (bloco == null) {
			bloco = new Bloco();
		}
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	public Set<Avaliacao> getAvaliacoes() {
		if (avaliacoes == null) {
			avaliacoes = new TreeSet<>();
		}
		return avaliacoes;
	}

	public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Morador))
			return false;
		Morador other = (Morador) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	
}