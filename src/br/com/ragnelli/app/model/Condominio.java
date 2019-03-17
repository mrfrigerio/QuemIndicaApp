package br.com.ragnelli.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Condominio implements Serializable {

	public enum TipoCondominio {
		Predios, Casas;
	}

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, length = 60)
	private String nome;

	@Embedded
	private Endereco endereco;

	@Embedded
	private Telefone telefone;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCondominio tipo;

	@OneToMany(mappedBy = "condominio", fetch = FetchType.EAGER)
	private List<Bloco> blocos;

	@OneToMany(mappedBy = "condominio")
	private Set<Usuario> usuarios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		if (telefone == null) {
			telefone = new Telefone();
		}
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public TipoCondominio getTipo() {
		return tipo;
	}

	public void setTipo(TipoCondominio tipo) {
		this.tipo = tipo;
	}

	public List<Bloco> getBlocos() {
		if (blocos == null) {
			blocos = new ArrayList<>();
		}

		if (blocos.size() > 0) {
			blocos.sort((e1, e2) -> e1.getNome().toUpperCase().compareTo(e2.getNome().toUpperCase()));
		}
		
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public Set<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new TreeSet<>();
		}
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Condominio))
			return false;
		Condominio other = (Condominio) obj;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		return true;
	}
	
	

}
