package br.com.ragnelli.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bloco implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, length = 60)
	private String nome;

	@ElementCollection
	@CollectionTable(name = "unidades", joinColumns = @JoinColumn(name = "bloco_id"))
	@Column(name = "unidade")
	private List<Integer> unidades;

	@ManyToOne
	@JoinColumn(name = "condominio_id")
	private Condominio condominio;

	public Bloco() {

	}

	public Bloco(String nome, List<Integer> unidades) {
		this.nome = nome;
		this.unidades = unidades;
	}

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

	public List<Integer> getUnidades() {
		if (unidades == null) {
			unidades = new ArrayList<>();
		}
		return unidades;
	}

	public void setUnidades(List<Integer> unidades) {
		this.unidades = unidades;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
	

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bloco))
			return false;
		Bloco other = (Bloco) obj;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
