package br.com.ragnelli.app.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Prestador extends Pessoa implements Serializable {

	@Embedded
	private Endereco endereco;

	private String Ramo;

	@OneToMany(mappedBy = "prestador")
	private Set<Avaliacao> avaliacoes;

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getRamo() {
		return Ramo;
	}

	public void setRamo(String ramo) {
		Ramo = ramo;
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
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Prestador))
			return false;
		Prestador other = (Prestador) obj;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		return true;
	}
	
	
	
}
